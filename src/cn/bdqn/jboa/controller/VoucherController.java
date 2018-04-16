package cn.bdqn.jboa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bdqn.jboa.biz.VoucherBiz;
import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.constans.Constants;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Result;
import cn.bdqn.jboa.entity.Voucher;
import cn.bdqn.jboa.entity.VoucherDetail;

@Controller
public class VoucherController {

	@Autowired
	private VoucherBiz voucherBiz;

	/**
	 * 报销单列表
	 * 
	 * @param status
	 * @param beginDate
	 * @param endDate
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("voucher_list")
	public String srarch(String status, String beginDate, String endDate,
			Integer pageIndex, Integer pageSize, ModelMap model,
			HttpSession session) {
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
		PageBean<Voucher> pageBean = voucherBiz.search(status, beginDate,
				endDate, employee, pageIndex, pageSize);
		model.put("pageBean", pageBean);
		model.put("status", status);
		model.put("beginDate", beginDate);
		model.put("endDate", endDate);
		model.put("pageIndex", pageIndex);
		model.put("pageSize", pageSize);
		return "jsp/claim/claim_voucher_list";
	}

	/**
	 * 到更新页面或添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("voucher_toEdit")
	public String toEdit(Integer id, ModelMap model) {
		if (id == null) {
			model.put("rowNumber", 0);
			return "jsp/claim/claim_voucher_edit";// 添加页面
		}
		Voucher voucher = voucherBiz.get(id);
		model.put("claimVoucher", voucher);
		model.put("rowNumber", voucher.getVoucherDetails().size());
		return "jsp/claim/claim_voucher_update";// 修改页面
	}

	/**
	 * 编辑报销单
	 * 
	 * @param voucher
	 * @return
	 */
	@RequestMapping("voucher_edit")
	public String edit(Voucher voucher, String[] item, Double[] account,
			String[] description, Integer[] oldId, HttpSession session,
			ModelMap model) {
		if (voucher.getTotalAccount() == null) {
			voucher.setTotalAccount(0.0);
		}
		if (voucher.getEvent() == null
				|| voucher.getEvent().trim().length() == 0) {
			voucher.setEvent("无");
		}
		Employee emp = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE);
		// 修改
		if (voucher.getId() != null && voucher.getId() >= 1) {
			Voucher dbVoucher = voucherBiz.get(voucher.getId());// 获取数据库中对象
			dbVoucher.setModifyTime(new Date());// 设置修改日期
			Set<VoucherDetail> tempDetails = dbVoucher.getVoucherDetails();// 临时集合，用来保存详情集合
			// 如果有修改之前的详情项
			if (oldId != null && oldId.length > 0) {
				List<VoucherDetail> vds = new ArrayList<VoucherDetail>();// 临时集合，用来比对详情项是否还存在
				for (VoucherDetail vd : tempDetails) {
					vds.add(vd);
				}
				// 遍历vds，决定是否要从集合中remove详情项
				for (VoucherDetail list_vd : vds) {
					boolean flag = false;
					for (int i = 0; i < oldId.length; i++) {
						if (oldId[i].intValue() == list_vd.getId().intValue()) {
							flag = true;
							break;
						}
					}
					// 找不到，则删除
					if (!flag) {
						tempDetails.remove(list_vd);
					}
				}
			} else {
				dbVoucher.getVoucherDetails().removeAll(tempDetails);// 清空所有
			}
			dbVoucher.setTotalAccount(voucher.getTotalAccount());
			dbVoucher.setEvent(voucher.getEvent());
			dbVoucher.setStatus(voucher.getStatus());
			addNewVoucherDetail(dbVoucher, item, account, description);
			voucherBiz.saveOrUpdate(dbVoucher);
		} else {
			// 新建
			voucher.setCreateTime(new Date());
			voucher.setEmployeeByCreateSn(emp);
			voucher.setEmployeeByNextDealSn(emp);
			addNewVoucherDetail(voucher, item, account, description);
			voucherBiz.saveOrUpdate(voucher);
		}
		if(voucher.getStatus().equals(Constants.VOUCHER_SUBMITTED)){
			voucher = voucherBiz.get(voucher.getId());
			voucherBiz.nextCheckEmp(voucher, Constants.POSITION_CN_FM,emp.getDepartment().getName(),Constants.VOUCHER_SUBMITTED );
		}
		return "redirect:voucher_list.do";
	}

	/**
	 * 添加详细项,新建及修改都需要
	 * 
	 * @param voucher
	 * @param item
	 * @param account
	 * @param description
	 */
	private void addNewVoucherDetail(Voucher voucher, String[] item,
			Double[] account, String[] description) {
		if (item != null && item.length > 0) {
			for (int i = 0; i < item.length; i++) {
				voucher.getVoucherDetails().add(
						new VoucherDetail(voucher, item[i], account[i],
								description[i]));
			}
		}
	}

	/**
	 * 提交报销单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("voucher_submit")
	public String submit(int id,HttpSession session) {
		Employee emp = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE);
		Voucher voucher = voucherBiz.get(id);
		voucherBiz.nextCheckEmp(voucher, Constants.POSITION_CN_FM,emp.getDepartment().getName(),Constants.VOUCHER_SUBMITTED );
		return "redirect:voucher_list.do";
	}
	
	/**
	 * 删除报销单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("voucher_deleteById")
	public String delete(int id) {
		voucherBiz.delete(id);
		return "redirect:voucher_list.do";
	}

	/**
	 * 查看报销单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("voucher_getVoucherById")
	public String getVoucherById(int id, ModelMap model) {
		Voucher voucher = voucherBiz.get(id);
		model.put("claimVoucher", voucher);
		return "jsp/claim/claim_voucher_view";
	}
	
	/**
	 * 到审核报销单页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("voucher_toCheck")
	public String toCheck(int id, ModelMap model) {
		Voucher voucher = voucherBiz.get(id);
		model.put("claimVoucher", voucher);
		return "jsp/claim/claim_voucher_check";
	}
	
	/**
	 * 审核报销单
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping("checkResult_checkVoucher")
	public String addCheckResult(Result result, HttpSession session) {
		// 添加审核结果
		result.setCheckTime(new Date());
		Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE);
		result.setCheckEmployee(employee);
		String res = result.getResult();
		if (result.getComm() == null || result.getComm().trim().length() == 0) {
			result.setComm(result.getResult());
		}
		Voucher voucher = voucherBiz.get(result.getVoucher().getId());
		voucher.getResults().add(result);
		String positionName, departmentName, status, nameCn = employee
				.getPosition().getNameCn();
		// 更改审核单状态
		if (res.equals(Constants.BACK)) {
			status = Constants.VOUCHER_BACK;
			voucherBiz.nextCheckEmp(voucher, voucher.getEmployeeByCreateSn(),
					status);
		} else if (res.equals(Constants.REFUSE)) {
			status = Constants.VOUCHER_TERMINATED;
			voucherBiz.nextCheckEmp(voucher, null, status);
		} else if (res.equals(Constants.PASS)) {
			// 根据处理人职位不同，分别处理
			if (nameCn.equals(Constants.POSITION_CN_FM) && voucher.getTotalAccount() > Constants.MONEY) {
				positionName = Constants.POSITION_CN_GM;
				departmentName = Constants.DEPARTMENT_DESIGN+","+Constants.DEPARTMENT_PRESIDENT;
				status = Constants.VOUCHER_WAIT;
			} else {
				positionName = Constants.POSITION_CN_CASHIER;
				departmentName = Constants.DEPARTMENT_FINANCE;
				status = Constants.VOUCHER_APPROVED;
			}
			voucherBiz.nextCheckEmp(voucher, positionName, departmentName,
					status);
		} else if (res.equals(Constants.PAYMENT)) {
			if (nameCn.equals(Constants.POSITION_CN_CASHIER)) {
				status = Constants.VOUCHER_PAID;
				voucherBiz.nextCheckEmp(voucher, null, status);
			}
		}
		return "redirect:voucher_list.do";
	}
}
