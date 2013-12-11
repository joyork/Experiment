package hive;

import org.apache.hadoop.hive.ql.exec.UDF;

public class MyAdd extends UDF{

	public String evaluate(int a,int b){
		return String.valueOf(a+b+100);
	}
}
