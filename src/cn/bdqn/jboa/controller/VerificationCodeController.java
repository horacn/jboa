package cn.bdqn.jboa.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bdqn.jboa.util.verification.VerificationCodeUtil;

/**
 * 验证码控制器
 * 
 * @author Administrator
 * 
 */
@Controller
public class VerificationCodeController {

	@Resource(name = "VerificationCodeUtil_Normal")
	private VerificationCodeUtil normal;
	@Resource(name = "VerificationCodeUtil_Clear")
	private VerificationCodeUtil clear;
	@Resource(name = "VerificationCodeUtil_Indefinite")
	private VerificationCodeUtil indefinite;
	@Resource(name = "VerificationCodeUtil_Large")
	private VerificationCodeUtil large;
	@Resource(name = "VerificationCodeUtil_Rotation")
	private VerificationCodeUtil rotation;
	@Resource(name = "VerificationCodeUtil_Math")
	private VerificationCodeUtil math;
	@Resource(name = "VerificationCodeUtil_Chinese_Italic")
	private VerificationCodeUtil italic;
	@Resource(name = "VerificationCodeUtil_Chinese_Regular")
	private VerificationCodeUtil regular;

	/**
	 * 获得验证码
	 * 
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping("verificationCode")
	public void getValidateCode(Integer codeId, HttpServletResponse response,
			HttpSession session) throws IOException {
		if(codeId==null){
			codeId = 1;
		}
		switch (codeId) {
		case 1:
			normal.createImage(response, session);
			break;
		case 2:
			clear.createImage(response, session);
			break;
		case 3:
			indefinite.createImage(response, session);
			break;
		case 4:
			large.createImage(response, session);
			break;
		case 5:
			rotation.createImage(response, session);
			break;
		case 6:
			math.createImage(response, session);
			break;
		case 7:
			italic.createImage(response, session);
			break;
		case 8:
			regular.createImage(response, session);
			break;
		default:
			normal.createImage(response, session);
			break;
		}
	}
}
