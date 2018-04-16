package cn.bdqn.jboa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.bdqn.jboa.biz.EmployeeBiz;
import cn.bdqn.jboa.constans.Constants;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.util.verification.VerificationCodeUtil;

/**
 * 账户控制器
 * @author Administrator
 *
 */
@Controller
public class AccountController {

	@Autowired
	private EmployeeBiz employeeBiz;
	
	/**
	 * 员工登录
	 * @param sn
	 * @param password
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String checkLogin(Employee emp,String verificationCode,ModelMap model,HttpSession session){
		Employee dbEmp = employeeBiz.getEmployeeBySn(emp.getSn());
		String sessionValidateCode = "";
		//获得session中的验证码
		sessionValidateCode = session.getAttribute(VerificationCodeUtil.RANDOMCODEKEY).toString();
		verificationCode = verificationCode!=null?verificationCode.trim().toLowerCase():null;
		String message = "";
		//工号不存在
		if(dbEmp==null){
			message = "工号不存在，请重新输入";
		}else{
			//密码不正确
			if(!dbEmp.getPassword().equalsIgnoreCase(emp.getPassword())){
				message = "密码不正确，请重新输入";
			}else{
				//验证码输入不正确
				if(!sessionValidateCode.equals(verificationCode)){
					message = "验证码不正确，请重新输入";
				}else{
					dbEmp.getDepartment().getName();
					dbEmp.getPosition().getNameEn();
					//登录成功
					session.setAttribute(Constants.SESSION_EMPLOYEE, dbEmp);
					return "redirect:index.jsp";
				}
			}
		}
		model.put("message",message);
		return "login";
	}
	
	/**
	 * 退出系统
	 * @param session
	 * @return
	 */
	@RequestMapping(value="exit")
	public String exit(HttpSession session){
		session.removeAttribute(Constants.SESSION_EMPLOYEE);
		return "redirect:login.jsp";
	}
}
