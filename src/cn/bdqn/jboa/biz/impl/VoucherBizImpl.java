package cn.bdqn.jboa.biz.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bdqn.jboa.biz.EmployeeBiz;
import cn.bdqn.jboa.biz.VoucherBiz;
import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.dao.VoucherDao;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Voucher;

@Service
public class VoucherBizImpl implements VoucherBiz {

	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private EmployeeBiz employeeBiz;

	public PageBean<Voucher> search(String status, String beginDate,
			String endDate, Employee employee, Integer pageIndex,
			Integer pageSize) {
		return voucherDao.search(status, beginDate, endDate, employee, pageIndex, pageSize);
	}
	
	public int getTotalCount(String status, String beginDate, String endDate,
			Employee employee) {
		return voucherDao.getTotalCount(status, beginDate, endDate, employee);
	}

	@Transactional
	public void saveOrUpdate(Voucher voucher) {
		voucherDao.saveOrUpdate(voucher);
	}

	@Transactional
	public void delete(Integer id) {
		voucherDao.delete(id);
	}

	public Voucher get(Integer id) {
		return voucherDao.get(id);
	}

	@Transactional
	public void nextCheckEmp(Voucher voucher,String positionName,String departmentName,String status) {
		//如果由多个部门，随机选择一个
		if(departmentName.contains(",")){
			String[] depts = departmentName.split(",");
			Random rand = new Random();
			int i = rand.nextInt(depts.length);
			departmentName = depts[i];
		}
		//获得下个处理人
		Employee nextDealEmp = employeeBiz.getEmployeeByDepartmentAndPosition(departmentName, positionName);
		voucher.setEmployeeByNextDealSn(nextDealEmp);
		voucher.setStatus(status);
		this.saveOrUpdate(voucher);
	}
	
	@Transactional
	public void nextCheckEmp(Voucher voucher,Employee nextDealEmp,String status) {
		voucher.setEmployeeByNextDealSn(nextDealEmp);
		voucher.setStatus(status);
		this.saveOrUpdate(voucher);
	}

}
