package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.AttributedString;
import java.util.Random;
import javax.imageio.ImageIO;

public class SimpleCaptcha {
	private Random generator = new Random();
	private final static String SIMPLE_CAPCHA_SESSION_KEY = "SIMPLE_CAPCHA_SESSION_KEY";

	private static char[] captchars = new char[] { 'a', 'b', 'c', 'd', 'e', '2',
	    '3', '4', '5', '6', '7', '8', 'g', 'f', 'y', 'n', 'm', 'n', 'p', 'w', 'x' };

	/**
	 * �����������
	 * 
	 * @return
	 */
	private Font getFont() {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, 45);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, 45);
		font[2] = new Font("Forte", Font.PLAIN, 45);
		font[3] = new Font("Wide Latin", Font.PLAIN, 40);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 45);
		return font[random.nextInt(5)];
	}

	/**
	 * ��������������ɫ
	 * 
	 * @return
	 */
	private Color getRandColor() {
		Random random = new Random();
		Color color[] = new Color[10];
		color[0] = new Color(32, 158, 25);
		color[1] = new Color(218, 42, 19);
		color[2] = new Color(31, 75, 208);
		return color[random.nextInt(3)];
	}

	public static void main(String[] args) {
		SimpleCaptcha sc = new SimpleCaptcha();
		int ImageWidth = 200;
		int ImageHeight = 100;

		int car = captchars.length - 1;
		/**
		 * ��������ַ���
		 */
		String test = "";
		for (int i = 0; i < 4; i++) {
			test += captchars[sc.generator.nextInt(car) + 1];
		}
		File image = new File("d://image.jpg");
		FileOutputStream fos;
    try {
	    fos = new FileOutputStream(image);


		BufferedImage bi = new BufferedImage(ImageWidth + 10, ImageHeight,
		    BufferedImage.TYPE_BYTE_INDEXED);

		Graphics2D graphics = bi.createGraphics();

		/**
		 * ���ñ���ɫ
		 */
		graphics.setColor(Color.white);

		graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());

		graphics.setColor(Color.black);
		AttributedString attstr = new AttributedString(test);

		TextLayout textTl = new TextLayout(test,
		    new Font("Courier", Font.BOLD, 70), new FontRenderContext(null, true,
		        false));
		AffineTransform textAt = graphics.getTransform();
		graphics.setFont(new Font("Courier", Font.BOLD, 70));
		graphics.setColor(sc.getRandColor());
		graphics.drawString(test, 10, 70);
		// textTl.draw(graphics, 4, 60);
		int w = bi.getWidth();
		int h = bi.getHeight();
		sc.shear(graphics, w, h, Color.white);
		// this.drawThickLine(graphics, 0, generator.nextInt(ImageHeight) + 1,
		// ImageWidth, generator.nextInt(ImageHeight) + 1, 4, Color.BLACK);

		ImageIO.write(bi, "JPEG", fos);
    } catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    }
	}

	private void shear(Graphics g, int w1, int h1, Color color) {

		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	public void shearX(Graphics g, int w1, int h1, Color color) {

		int period = generator.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = generator.nextInt(2);

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

	public void shearY(Graphics g, int w1, int h1, Color color) {

		int period = generator.nextInt(40) + 10; // 50;

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

	private void drawThickLine(Graphics g, int x1, int y1, int x2, int y2,
	    int thickness, Color c) {

		// The thick line is in fact a filled polygon
		g.setColor(c);
		int dX = x2 - x1;
		int dY = y2 - y1;
		// line length
		double lineLength = Math.sqrt(dX * dX + dY * dY);

		double scale = (double) (thickness) / (2 * lineLength);

		// The x and y increments from an endpoint needed to create a
		// rectangle...
		double ddx = -scale * (double) dY;
		double ddy = scale * (double) dX;
		ddx += (ddx > 0) ? 0.5 : -0.5;
		ddy += (ddy > 0) ? 0.5 : -0.5;
		int dx = (int) ddx;
		int dy = (int) ddy;

		// Now we can compute the corner points...
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];

		xPoints[0] = x1 + dx;
		yPoints[0] = y1 + dy;
		xPoints[1] = x1 - dx;
		yPoints[1] = y1 - dy;
		xPoints[2] = x2 - dx;
		yPoints[2] = y2 - dy;
		xPoints[3] = x2 + dx;
		yPoints[3] = y2 + dy;

		g.fillPolygon(xPoints, yPoints, 4);
	}

}