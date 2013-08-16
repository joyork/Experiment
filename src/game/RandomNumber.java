package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public class RandomNumber {
	public static void main(String[] args) {
		makeImage();
	}
	private int getFontSize(Random random){
		int size = (int)(random.nextFloat()*3)+20;
		return size;
	}
	
	private String getFontType(Random random){
		int type = (int)(random.nextFloat()*(fontTypes.length-1));
		return fontTypes[type];
	}
	
	private static final String[] fontTypes = {"\u5b8b\u4f53" , "\u65b0\u5b8b\u4f53" , "\u9ed1\u4f53" , "\u6977\u4f53" , "\u96b6\u4e66" };

	public static void makeImage() {
		RandomNumber rm = new RandomNumber();
		Random random1 = new Random();

		int width = 100, height = 35;
		BufferedImage image = new BufferedImage(width, height,
		    BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
    g.setColor(new Color(0xFFFFFF));
		g.fillRect(0, 0, width, height);
		// g.setColor(Color.black);
		// g.drawRect(0,0,width-1,height-1);
		g.setColor(Color.black);

		String rand = "干e已5";

		for (int i = 0; i < rand.length(); i++) {
			g.setFont(new Font(rm.getFontType(random1), Font.BOLD, rm
			    .getFontSize(random1)));
			String Str = rand.substring(i, i + 1);
			int w = 20 * i;
			if (i % 2 == 1) {
				w = 20 * i + 5;
			}
			g.setColor(new Color((int) (random1.nextFloat() * 50 + 50),
			    (int) (random1.nextFloat() * 50 + 50),
			    (int) (random1.nextFloat() * 50 + 50)));
			g.drawString(Str, (int) (random1.nextFloat() * 10) + w, (int) (random1
					.nextFloat() * 5) + 22);
		}

		// 随机产生88个干扰点，使图象中的认证码不易被其它程序探测到
		Random random = new Random();
		for (int i = 0; i < 1; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.setColor(new Color(random1.nextFloat(), random1.nextFloat(), random1
			    .nextFloat()));
			g.drawLine(0, y, width, random.nextInt(height));
			g.drawLine(x, 0, random.nextInt(width), height);
		}
//		for (int i = 0; i < 18; i++) {
//			int x = random.nextInt(width);
//			int y = random.nextInt(height);
//			g.setColor(new Color(random1.nextFloat(), random1.nextFloat(), random1
//			    .nextFloat()));
//			g.drawOval(x, y, 1, 1);
//		}
    

    
		int w = image.getWidth();
		int h = image.getHeight();
		shear(g, w, h, Color.white);
		// 图象生效

		g.dispose();

		// 输出图象到页面

		try {
			File file = new File("D://test.jpg");
			FileOutputStream fos = new FileOutputStream(file);
			ImageIO.write(image, "JPEG", fos);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void shear(Graphics g, int w1, int h1, Color color) {

		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	public static void shearX(Graphics g, int w1, int h1, Color color) {
		Random rm = new Random();
		int period = rm.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = rm.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
			    * Math.sin((double) i / (double) period
			        + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	public static void shearY(Graphics g, int w1, int h1, Color color) {
		Random rm = new Random();
		int period = rm.nextInt(13) + 4; 

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
			    * Math.sin((double) i / (double) period
			        + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}

	}
}