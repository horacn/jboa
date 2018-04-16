package cn.bdqn.jboa.util.verification;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码工具抽象类,可有多个解决方案
 * 
 * @author user
 * 
 */
public abstract class VerificationCodeUtil {

	/**
	 * 图形验证码的字符集合，系统将随机从这个字符串中选择一些字符作为验证码
	 */
	private String code = "%#23456789abcdefghkmnpqrstuvwxyzABCDEFGHKLMNPQRSTUVWXYZ";
	/**
	 * 验证码字符长度
	 */
	private int size = 4;
	/**
	 * 干扰线个数
	 */
	private int lines = 30;
	/**
	 * 图片宽度
	 */
	private int width = 65;
	/**
	 * 图片高度
	 */
	private int height = 22;
	/**
	 * 字体大小
	 */
	private int fontSize = 16;
	/**
	 * 图片类型
	 */
	private String pictureType = "JPEG";
	/**
	 * 验证码在session中的key
	 */
	public static String RANDOMCODEKEY = "randCode";
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getPictureType() {
		return pictureType;
	}

	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}

	/**
	 * 获取验证码
	 * 
	 * @param response
	 * @param session
	 */
	public abstract void createImage(HttpServletResponse response,
			HttpSession session) throws IOException;
}