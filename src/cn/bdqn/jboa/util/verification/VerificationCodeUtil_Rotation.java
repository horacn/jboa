package cn.bdqn.jboa.util.verification;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;


/**
 * 验证码工具类，特点：文字可旋转
 * 
 * @author hezhao
 * 
 */
@Component("VerificationCodeUtil_Rotation")
public class VerificationCodeUtil_Rotation extends VerificationCodeUtil {

	/**
	 * 获得验证码
	 * 
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void createImage(HttpServletResponse response, HttpSession session)
			throws IOException {

		// 设制不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成图片
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		Font mFont = new Font("宋体", Font.BOLD, getFontSize());
		g.setColor(this.getRandColor(200, 250));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setFont(mFont);
		g.setColor(this.getRandColor(180, 200));

		this.getRandLine(g2d);

		String randCode = this.getRandWord(g);

		session.setAttribute(RANDOMCODEKEY, randCode.toLowerCase());

		g.dispose();

		ImageIO.write(image, this.getPictureType(), response.getOutputStream());

	}

	/**
	 * 生成随机颜色
	 * 
	 * @param start
	 *            [int]
	 * @param end
	 *            [int]
	 * @return Color [object]
	 */
	private Color getRandColor(int start, int end) {
		Random rand = new Random();
		int randNum;
		if (start > 255)
			start = 255;
		if (end > 255)
			end = 255;
		if (start > end)
			randNum = start - end;
		else
			randNum = end - start;
		int r = start + rand.nextInt(randNum);
		int g = start + rand.nextInt(randNum);
		int b = start + rand.nextInt(randNum);
		return new Color(r, g, b);
	}

	/**
	 * 着色\旋转\缩放
	 * 
	 * @param word
	 *            文字
	 * @param g
	 *            图片对象
	 */
	private void coloredAndRotation(String word, int i, Graphics g) {
		Random rand = new Random();
		/** 着色 **/
		g.setColor(new Color(20 + rand.nextInt(110), 20 + rand.nextInt(110),
				20 + rand.nextInt(110)));
		/** 旋转 **/
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform trans = new AffineTransform();
		trans.rotate(rand.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
		/** 缩放 **/
		float scaleSize = rand.nextFloat() + 0.8f;
		if (scaleSize > 1f)
			scaleSize = 1f;
		trans.scale(scaleSize, scaleSize);
		g2d.setTransform(trans);
		g.drawString(word, 15 * i + 20, 20);
	}

	/**
	 * 生成干扰线
	 * 
	 * @param g2d
	 */
	private void getRandLine(Graphics2D g2d) {
		Random rand = new Random();
		for (int i = 0; i < this.getLines(); i++) {
			int x = rand.nextInt(getWidth() - 1);
			int y = rand.nextInt(getHeight() - 1);
			int z = rand.nextInt(6) + 1;
			int w = rand.nextInt(12) + 1;

			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);
			Line2D line = new Line2D.Double(x, y, x + z, y + w);
			g2d.setStroke(bs);
			g2d.draw(line);
		}
	}

	/**
	 * 获取随机文字
	 * 
	 * @param g
	 *            [Graphics] 图片对象
	 * @return String
	 * @case1:A-Z
	 * @case2:chinese
	 * @default:0-9
	 */
	private String getRandWord(Graphics g) {
		Random rand = new Random();
		String finalWord = "", firstWord = "";
		int tempInt = 0;
		for (int i = 0; i < getSize(); i++) {
			switch (rand.nextInt(2)) {
			case 1:
				tempInt = rand.nextInt(26) + 65;
				firstWord = String.valueOf((char) tempInt);
				break;
			default:
				tempInt = rand.nextInt(10) + 48;
				firstWord = String.valueOf((char) tempInt);
				break;
			}
			finalWord += firstWord;
			this.coloredAndRotation(firstWord, i, g);
		}

		return finalWord;
	}

	/**
	 * 获得干扰线个数
	 */
	public int getLines() {
		return 100;
	}

	/**
	 * 获得图片宽度
	 */
	public int getWidth() {
		return 100;
	}

	/**
	 * 获得图片高度
	 */
	public int getHeight() {
		return 40;
	}

	/**
	 * 获得字体大小
	 */
	public int getFontSize() {
		return 22;
	}
}
