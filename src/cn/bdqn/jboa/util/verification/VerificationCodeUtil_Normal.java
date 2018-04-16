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
 * 验证码工具类，普通版
 * 
 * @author hezhao
 * 
 */
@Component("VerificationCodeUtil_Normal")
public class VerificationCodeUtil_Normal extends VerificationCodeUtil {
	
	/**
	 * 获得验证码
	 * 
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void createImage(HttpServletResponse response, HttpSession session)
			throws IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 在内存中创建图象
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, getFontSize()));
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);
		g.setColor(getRandColor(160, 200));
		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		getRandLine(g, random);
		String sRand = "";
		// 取随机产生的验证码(由数字和字母组长的)
		sRand = getRandWord(g, random, sRand);
		// 将认证码存入SESSION
		session.setAttribute(RANDOMCODEKEY, sRand.toLowerCase());
		// 图象生效
		g.dispose();
		// 输出图象到页面
		ImageIO.write(image, this.getPictureType(), response.getOutputStream());
	}

	/**
	 * 随机产生验证码
	 * 
	 * @param g
	 * @param random
	 * @param sRand
	 * @return
	 */
	private String getRandWord(Graphics g, Random random, String sRand) {
		for (int i = 0; i < getSize(); i++) {
			int rand = random.nextInt(getCode().length());
			sRand += String.valueOf(getCode().charAt(rand));
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(String.valueOf(getCode().charAt(rand)), 13 * i + 8, 18);
		}
		return sRand;
	}

	/**
	 * 画干扰线
	 * 
	 * @param g
	 * @param random
	 */
	private void getRandLine(Graphics g, Random random) {

		for (int i = 0; i < this.getLines(); i++) {
			int x = random.nextInt(this.getWidth());
			int y = random.nextInt(this.getHeight());
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	/**
	 * 获得干扰线个数
	 */
	public int getLines() {
		return 45;
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
		return 24;
	}

	/**
	 * 获得字体大小
	 */
	public int getFontSize() {
		return 23;
	}
}
