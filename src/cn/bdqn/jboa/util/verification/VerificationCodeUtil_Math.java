package cn.bdqn.jboa.util.verification;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.mchange.v1.util.ArrayUtils;


/**
 * 验证码工具类，数学版
 * 
 * @author hezhao
 * 
 */
@Component("VerificationCodeUtil_Math")
public class VerificationCodeUtil_Math extends VerificationCodeUtil {

	/**
	 * 最小数
	 */
	private final int minNum = 1;

	/**
	 * 最大数
	 */
	private final int maxNum = 99;

	/**
	 * 运算符
	 */
	private final char[] signs = { '+', '-', '×', '÷' };

	/**
	 * 获得一个运算符验证码，将运算结果存入session中
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
		BufferedImage image = new BufferedImage(this.getWidth(),
				this.getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= this.getLines(); i++) {
			drowLine(g);
		}
		// 绘制随机字符
		String result = String.valueOf(colcResult(g));
		session.removeAttribute(RANDOMCODEKEY);
		session.setAttribute(RANDOMCODEKEY, result);
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
	 * 计算结果
	 */
	private int colcResult(Graphics g) {
		g.setFont(getFont());
		Random random = new Random();
		// 获得运算符号
		char sign = signs[random.nextInt(signs.length)];
		int x = 0;
		int y = 0;
		int maxNum2 = maxNum;
		// 获得随机数字
		switch (sign) {
		case '÷':
			while (true) {
				x = random.nextInt(maxNum2) % (maxNum2 - minNum + 1) + minNum;
				y = random.nextInt(maxNum2) % (maxNum2 - minNum + 1) + minNum;
				// 如果被除数大于除数 且 整除除数q 且 除数不等于1，就退出循环
				if (x > y && x%y==0 && y!=1) {
					break;
				}
			}
			break;
		case '×':
			// 如果是乘号，就把最大数改为20，即20以内的乘法
			maxNum2 = 20;
		default:
			x = random.nextInt(maxNum2) % (maxNum2 - minNum + 1) + minNum;
			y = random.nextInt(maxNum2) % (maxNum2 - minNum + 1) + minNum;
			break;
		}
		String[] chars = { String.valueOf(x), String.valueOf(sign),
				String.valueOf(y), "=", "?" };
		int length = 1;// 文字间隔长度
		for (int i = 0; i < chars.length; i++) {
			drowPicture(g, random, length, chars[i]);
			length += chars[i].length();
		}
		// 计算结果
		int result = 0;
		switch (sign) {
		case '+':
			result = x + y;
			break;
		case '-':
			result = x - y;
			break;
		case '×':
			result = x * y;
			break;
		case '÷':
			result = x / y;
			break;
		}
		return result;
	}

	/**
	 * 绘制图片
	 */
	private void drowPicture(Graphics g, Random random, int i, String rand) {
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
				.nextInt(121)));
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 18);
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
	 * 获得干扰线个数
	 */
	public int getLines() {
		return 10;
	}

	/**
	 * 获得图片宽度
	 */
	public int getWidth() {
		return 120;
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