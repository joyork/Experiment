package image;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.DynamicOperation;
import org.im4java.core.GraphicsMagickCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.Operation;
import org.im4java.process.ArrayListOutputConsumer;

public class GraphicImagickTools {
	
	public static void resizeImage(String srcPath, String desPath, double zipRate, int width, int height)
			throws Exception {

		if (width <= 0 || height <= 0) {
			return;
		}

		IMOperation op = new IMOperation();
		op.addImage();
		op.resize(width, height);

		op.quality(zipRate);
		op.addImage();

		ConvertCmd convert = new ConvertCmd(true);
		convert.run(op, srcPath, desPath);

	}
	
	/**
	 * 以out方式组合图片
	 * @param srcPath
	 * @param srcPath2
	 * @param desPath
	 * @throws Exception
	 */
	public static void composeOut(String srcPath, String srcPath2,String desPath)
		throws Exception {
		
		IMOperation op = new IMOperation();
		op.compose("out");
		op.addImage();
		op.addImage();
		op.addImage();
		GraphicsMagickCmd composite = new GraphicsMagickCmd("composite");
		composite.createScript("cut-corner",op);
		composite.run(op, srcPath,srcPath2,desPath);
	}
	
	/**
	 * 缩略图
	 * @param width		缩略宽度
	 * @param height	缩略高度
	 * @param src
	 * @param des
	 * @throws Exception
	 */
	public static void thumbnail(Integer width ,Integer height, String src,String des) throws Exception{

		IMOperation op = new IMOperation();
		op.thumbnail(width, height); 
		op.addImage();
		op.addImage();
		
		GraphicsMagickCmd convert = new GraphicsMagickCmd("convert");
		convert.createScript("convert-image", op);
		convert.run(op, src, des);
	}
	
	/**
	 * 扩展图片
	 * @param width		resize-x
	 * @param height	resize-y
	 * @param x		offset-x
	 * @param y		offset-y
	 * @param src	源文件
	 * @param ext	填充背景
	 * @param des	目标文件
	 * @throws Exception
	 */
	public static void expand(Integer width,Integer height, Integer x, Integer y, String src,String ext,String des) throws Exception{
		
		IMOperation op = new IMOperation();
		op.geometry(width, height, x, y);
		op.addImage();
		op.addImage();
		op.addImage();
		GraphicsMagickCmd composite = new GraphicsMagickCmd("composite");
		composite.createScript("expand",op);
		composite.run(op, src,ext,des);
		
	}
	
	public static void main(String[] args) {
		final String mask = "D:\\demo\\cornermask.png";
		final String ext = "D:\\demo\\expander.png";
		String src = "D:\\demo\\test.jpg";
		String corner = "D:\\demo\\out.png";
		String thumb = "D:\\demo\\out_104.png";
		String des = "D:\\demo\\final.png";
		String srcPath = "D:/demo/icon1.png";
		String desPath = "D:/demo/icon_do.png";
		double zipRate = 100.0;
		int width = 175;
		int height = 175;
		try {
			resizeImage(srcPath, desPath, zipRate, width, height);
			composeOut(desPath, mask, corner);
			thumbnail(104, 104, corner, thumb);
			expand(104, 104, 18, 0, thumb, ext, des);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
