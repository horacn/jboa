package cn.bdqn.jboa.util.verification;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;


/**
 * 验证码工具类，特点:随机生成3个到5个验证码,随机字体
 * 
 * @author hezhao
 * 
 */
@Component("VerificationCodeUtil_Indefinite")
public class VerificationCodeUtil_Indefinite extends VerificationCodeUtil {
	
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

		// 获得验证码集合的长度
		int charsLength = getCode().length();
		// 设置图形验证码的长和宽（图形的大小）
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();// 获得用于输出文字的Graphics对象

		g.setColor(getRandomColor(180, 250));// 随机设置要填充的颜色
		g.fillRect(0, 0, this.getWidth(), this.getHeight());// 填充图形背景
		// 设置初始字体
		g.setFont(new Font("Times New Roman", Font.ITALIC, getFontSize()));
		g.setColor(getRandomColor(120, 180));// 随机设置字体颜色
		getRandLine(g);// 画干扰线
		// 用于保存最后随机生成的验证码
		StringBuilder validationCode = new StringBuilder();
		// 验证码的随机字体
		String[] fontNames = { "Times New Roman", "Book antiqua", "Arial",
				"Consolas", "Calibri", "Cambria", "Segoe Marker" };
		// 获得随机文字
		getRandWord(charsLength, g, validationCode, fontNames);

		session.setAttribute(RANDOMCODEKEY, validationCode.toString()
				.toLowerCase());

		ImageIO.write(image, this.getPictureType(), response.getOutputStream());
		g.dispose();
	}

	/**
	 * 返回一个随机颜色（Color对象）
	 * 
	 * @param minColor
	 * @param maxColor
	 * @return
	 */
	private Color getRandomColor(int minColor, int maxColor) {
		Random random = new Random();
		// 保存minColor最大不会超过255
		if (minColor > 255)
			minColor = 255;
		// 保存minColor最大不会超过255
		if (maxColor > 255)
			maxColor = 255;
		// 获得红色的随机颜色值
		int red = minColor + random.nextInt(maxColor - minColor);
		// 获得绿色的随机颜色值
		int green = minColor + random.nextInt(maxColor - minColor);
		// 获得蓝色的随机颜色值
		int blue = minColor + random.nextInt(maxColor - minColor);
		return new Color(red, green, blue);
	}

	/**
	 * 获得随机文字
	 * 
	 * @param charsLength
	 * @param g
	 * @param random
	 * @param validationCode
	 * @param fontNames
	 */
	private void getRandWord(int charsLength, Graphics g,
			StringBuilder validationCode, String[] fontNames) {
		Random random = new Random();
		// 随机生成3个到5个验证码
		for (int i = 0; i < 3 + random.nextInt(3); i++) {
			// 随机设置当前验证码的字符的字体
			g.setFont(new Font(fontNames[random.nextInt(fontNames.length)], Font.ITALIC,
					getFontSize()));
			// 随机获得当前验证码的字符
			char codeChar = getCode().charAt(random.nextInt(charsLength));
			validationCode.append(codeChar);
			// 随机设置当前验证码字符的颜色
			g.setColor(getRandomColor(10, 100));
			// 在图形上输出验证码字符，x和y都是随机生成的
			g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7),
					(int)(getHeight()*0.8) - random.nextInt(6));
		}
	}

	/**
	 * 生成干扰线
	 * 
	 * @param graphic
	 */
	private void getRandLine(Graphics graphic) {
		Random rand = new Random();
		for (int i = 1; i <= this.getLines(); i++) {
			graphic.setColor(getRandomColor(10, 100));
			graphic.drawLine(rand.nextInt(this.getWidth()), rand.nextInt(this.getHeight()),
					rand.nextInt(this.getWidth()), rand.nextInt(this.getHeight()));
		}
	}
	
	/**
	 * 获得干扰线个数
	 */
	public int getLines() {
		return 5;
	}

	/**
	 * 获得图片宽度
	 */
	public int getWidth() {
		return 105;
	}

	/**
	 * 获得图片高度
	 */
	public int getHeight() {
		return 35;
	}

	/**
	 * 获得字体大小
	 */
	public int getFontSize() {
		return 22;
	}
}