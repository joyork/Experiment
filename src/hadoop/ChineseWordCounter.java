package hadoop;

import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class ChineseWordCounter extends Configured implements Tool {

	public static class Map extends Mapper<LongWritable,Text,Text,IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		Analyzer analyzer = new IKAnalyzer(true);  
		
		public void map(LongWritable key, Text value, Context context) throws IOException,
			InterruptedException{
			String line = value.toString();
			StringReader in = new StringReader(line);
			TokenStream ts = analyzer.tokenStream("", in);
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);
                
			while (ts.incrementToken()) {
			  char[] termBuffer = termAtt.termBuffer();
			  int termLen = termAtt.termLength();
			  String w = new String(termBuffer, 0, termLen);
			  if(w.length()>1){
				  word.set(w); 
				  context.write(word, one);
			  }
			}
			
//			StringTokenizer tokenizer = new StringTokenizer(line);
//			while (tokenizer.hasMoreTokens()) {
//				word.set(tokenizer.nextToken());
//				context.write(word, one);
//			}
		}
	}
	
	public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>{
		public void reduce(Text key,Iterable<IntWritable> values,Context context)
		throws IOException,InterruptedException{
			int sum = 0;
			for(IntWritable val : values){
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}
	
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		conf.set("mapred.job.queue.name", "q_mobileapp");
		Job job = new Job(conf);
		job.setJarByClass(ChineseWordCounter.class);
		job.setJobName("wordcount");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean success = job.waitForCompletion(true);
		return success?0:1;
	}

	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new ChineseWordCounter(), args);
		System.exit(ret);
	}
}
