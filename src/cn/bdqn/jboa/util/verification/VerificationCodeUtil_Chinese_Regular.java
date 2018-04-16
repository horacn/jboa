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
 * 验证码工具类，中文版,楷体
 * 
 * @author hezhao
 * 
 */
@Component("VerificationCodeUtil_Chinese_Regular")
public class VerificationCodeUtil_Chinese_Regular extends VerificationCodeUtil {
	
	/**
	 * 汉字的取值范围
	 */
	private final String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 获得汉字验证码
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
		g.setFont(new Font("宋体", Font.PLAIN, getFontSize()));
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);
		g.setColor(getRandColor(160, 200));

		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		getRandLine(g, random);
		String sRand = "";
		// 取随机产生的验证码(由数字和字母组长的)
		sRand = getRandWord(g, random);
		// 将认证码存入SESSION
		session.setAttribute(RANDOMCODEKEY, sRand);
		// 图象生效
		g.dispose();
		// 输出图象到页面
		ImageIO.write(image, this.getPictureType(), response.getOutputStream());
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
	 * 随机产生验证码
	 * 
	 * @param g
	 * @param random
	 * @param sRand
	 * @return
	 */
	public String getRandWord(Graphics g, Random random) {
		StringBuffer sRand = new StringBuffer();
		int x = 5;
		for (int i = 0; i < getSize(); i++) {
			String hanzi = createHanZi();// 获得汉字
			sRand.append(hanzi);
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(hanzi, x, 24);
			x += 23;// 每写一个字，下次写字的起始点要改变
		}
		return sRand.toString();
	}

	/**
	 * 随机产生干扰线
	 * 
	 * @param g
	 * @param random
	 */
	public void getRandLine(Graphics g, Random random) {
		for (int i = 0; i < this.getLines(); i++) {
			int x = random.nextInt(this.getWidth());
			int y = random.nextInt(this.getHeight());
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
	}

	/**
	 * 生成汉字
	 * 
	 * @param args
	 * @return
	 */
	private String createHanZi() {
		String code = "";
		Random random = new Random();
		// 生成第1位的区码
		int r1 = random.nextInt(3) + 11; // 生成11到14之间的随机数
		String str_r1 = rBase[r1];
		// 生成第2位的区码
		int r2;
		if (r1 == 13) {
			r2 = random.nextInt(7); // 生成0到7之间的随机数
		} else {
			r2 = random.nextInt(16); // 生成0到16之间的随机数
		}
		String str_r2 = rBase[r2];
		// 生成第1位的位码
		int r3 = random.nextInt(6) + 10; // 生成10到16之间的随机数
		String str_r3 = rBase[r3];
		// 生成第2位的位码
		int r4;
		if (r3 == 10) {
			r4 = random.nextInt(15) + 1; // 生成1到16之间的随机数
		} else if (r3 == 15) {
			r4 = random.nextInt(15); // 生成0到15之间的随机数
		} else {
			r4 = random.nextInt(16); // 生成0到16之间的随机数
		}
		String str_r4 = rBase[r4];
		//System.out.println(str_r1 + str_r2 + str_r3 + str_r4);

		// 将生成机内码转换为汉字
		byte[] bytes = new byte[2];
		// 将生成的区码保存到字节数组的第1个元素中
		String str_r12 = str_r1 + str_r2;
		int tempLow = Integer.parseInt(str_r12, 16);
		bytes[0] = (byte) tempLow;
		// 将生成的位码保存到字节数组的第2个元素中
		String str_r34 = str_r3 + str_r4;
		int tempHigh = Integer.parseInt(str_r34, 16);
		bytes[1] = (byte) tempHigh;

		code = new String(bytes); // 根据字节数组生成汉字
		return code;
	}
	
	/**
	 * 获得干扰线个数
	 */
	public int getLines() {
		return 20;
	}

	/**
	 * 获得图片宽度
	 */
	public int getWidth() {
		return 110;
	}

	/**
	 * 获得图片高度
	 */
	public int getHeight() {
		return 34;
	}

	/**
	 * 获得字体大小
	 */
	public int getFontSize() {
		return 20;
	}
}