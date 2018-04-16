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
 * 验证码工具类，大图版
 * 
 * @author hezhao
 * 
 */
@Component("VerificationCodeUtil_Large")
public class VerificationCodeUtil_Large extends VerificationCodeUtil {

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
		Graphics graphic = image.getGraphics();
		graphic.setColor(Color.LIGHT_GRAY);
		graphic.fillRect(0, 0, this.getWidth(), this.getHeight());

		StringBuffer sb = new StringBuffer();
		// 获取随机文字
		getRandWord(sb, graphic);
		// 画干扰线
		getRandLine(graphic);
		session.setAttribute(RANDOMCODEKEY, sb.toString().toLowerCase());
		ImageIO.write(image, this.getPictureType(), response.getOutputStream());
	}

	/**
	 * 获取随机文字
	 * 
	 * @param sb
	 * @param graphic
	 * @param ran
	 */
	private void getRandWord(StringBuffer sb, Graphics graphic) {
		Random ran = new Random();
		// 画随机字符
		for (int i = 1; i <= getSize(); i++) {
			int r = ran.nextInt(getCode().length());
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font("Times New Roman",
					Font.BOLD + Font.ITALIC, getFontSize()));
			graphic.drawString(String.valueOf(getCode().charAt(r)), (i - 1) * getWidth()
					/ getSize(), (int)(getHeight()/1.5));
			sb.append(String.valueOf(getCode().charAt(r)));// 将字符保存，存入Session
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
			graphic.setColor(getRandomColor());
			graphic.drawLine(rand.nextInt(this.getWidth()), rand.nextInt(this.getHeight()),
					rand.nextInt(this.getWidth()), rand.nextInt(this.getHeight()));
		}
	}

	/**
	 * 获得随机颜色
	 * 
	 * @return
	 */
	private Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(156), ran.nextInt(156),
				ran.nextInt(156));
		return color;
	}
	
	/**
	 * 获得干扰线个数
	 */
	public int getLines() {
		return 7;
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
		return 50;
	}

	/**
	 * 获得字体大小
	 */
	public int getFontSize() {
		return 30;
	}

	public int getSize() {
		return 5;
	}
	
}