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
 * 验证码工具类，清晰版
 * 
 * @author hezhao
 * 
 */
@Component("VerificationCodeUtil_Clear")
public class VerificationCodeUtil_Clear extends VerificationCodeUtil {

	/**
	 * 获得验证码
	 * 
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void createImage(HttpServletResponse response, HttpSession session)
			throws IOException {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);

		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= this.getLines(); i++) {
			drowLine(g);
		}
		// 绘制随机字符
		String randomString = "";
		for (int i = 1; i <= getSize(); i++) {
			randomString = drowString(g, randomString, i);
		}
		session.removeAttribute(RANDOMCODEKEY);
		session.setAttribute(RANDOMCODEKEY, randomString.toLowerCase());
		g.dispose();
		// 将内存中的图片通过流动形式输出到客户端
		ImageIO.write(image, this.getPictureType(), response.getOutputStream());
	}

	/**
	 * 获得字体
	 */
	private Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, getFontSize());
	}
	/**
	 * 获得颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/**
	 * 绘制字符串
	 */
	private String drowString(Graphics g, String randomString, int i) {
		Random random = new Random();
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
				.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(getCode()
				.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 18);
		return randomString;
	}

	/**
	 * 绘制干扰线
	 */
	private void drowLine(Graphics g) {
		Random random = new Random();
		int x = random.nextInt(this.getWidth());
		int y = random.nextInt(this.getHeight());
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	/**
	 * 获取随机的字符
	 */
	private String getRandomString(int num) {
		return String.valueOf(getCode().charAt(num));
	}
	
	/**
	 * 获得干扰线个数
	 */
	public int getLines() {
		return 15;
	}

	/**
	 * 获得图片宽度
	 */
	public int getWidth() {
		return 80;
	}

	/**
	 * 获得图片高度
	 */
	public int getHeight() {
		return 27;
	}

	/**
	 * 获得字体大小
	 */
	public int getFontSize() {
		return 20;
	}
}