package cn.bdqn.jboa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bdqn.jboa.biz.EmployeeBiz;
import cn.bdqn.jboa.biz.LeaveBiz;
import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.constans.Constants;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Leave;
import cn.bdqn.jboa.entity.Voucher;

@Controller
public class LeaveController {

	@Autowired
	private LeaveBiz leaveBiz;
	@Autowired
	private EmployeeBiz employeeBiz;
	
	/**
	 * 到添加请假页面
	 * @return
	 */
	@RequestMapping("leave_toAdd")
	public String toAdd(HttpSession session,ModelMap model){
		Employee employee =  (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE);
		String deptName = employee.getDepartment().getName();
		//获得审批人
		Employee nextDealEmp = employeeBiz.getEmployeeByDepartmentAndPosition(deptName, Constants.POSITION_CN_FM);
		model.put("manager", nextDealEmp);
		return "jsp/leave/leave_edit";
	}

	/**
	 * 添加请假信息
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("leave_add")
	public String add(Leave leave,String startTime,String endTime,HttpSession session) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		leave.setStarttime(format.parse(startTime));
		leave.setEndtime(format.parse(endTime));
		leave.setStatus(Constants.LEAVE_STATUS_WAIT);
		leave.setCreatetime(new Date());
		leaveBiz.saveOrUpdate(leave);
		return "redirect:leave_list.do";
	}
	
	
	/**
	 * 到审批页面
	 * @return
	 */
	@RequestMapping("leave_toCheck")
	public String toCheck(int id,ModelMap model){
		Leave leave = leaveBiz.get(id);
		model.put("leave", leave);
		return "jsp/leave/leave_check";
	}
	
	/**
	 * 审批
	 * @return
	 */
	@RequestMapping("leave_checkLeave")
	public String checkLeave(Leave leave){
		Leave dbLeave = leaveBiz.get(leave.getId());
		dbLeave.setStatus(leave.getStatus());
		dbLeave.setApproveOpinion(leave.getApproveOpinion());
		dbLeave.setModifytime(new Date());
		leaveBiz.saveOrUpdate(dbLeave);
		return "redirect:leave_list.do";
	}
	
	/**
	 * 查看请假列表
	 * @param leavetype
	 * @param beginDate
	 * @param endDate
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("leave_list")
	public String search(String status,String leavetype, String beginDate, String endDate,
			Integer pageIndex, Integer pageSize, ModelMap model,
			HttpSession session){
		if (session.getAttribute(Constants.SESSION_EMPLOYEE) == null) {
			return "redirect:login.jsp";
		}
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = 5;
		}
		Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE);
		PageBean<Leave> pageBean = leaveBiz.search(status,leavetype, beginDate, endDate, employee, pageIndex, pageSize);
		model.put("pageBean", pageBean);
		model.put("status", status);
		model.put("leavetype", leavetype);
		model.put("beginDate", beginDate);
		model.put("endDate", endDate);
		model.put("pageIndex", pageIndex);
		model.put("pageSize", pageSize);
		return "jsp/leave/leave_list";
	}
	
	/**
	 * 查看详细信息
	 * @return
	 */
	@RequestMapping("leave_getLeaveById")
	public String getLeaveById(int id,ModelMap model){
		Leave leave = leaveBiz.get(id);
		model.put("leave", leave);
		return "jsp/leave/leave_view";
	}
}
