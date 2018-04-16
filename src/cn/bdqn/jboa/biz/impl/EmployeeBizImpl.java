package cn.bdqn.jboa.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.jboa.biz.EmployeeBiz;
import cn.bdqn.jboa.dao.EmployeeDao;
import cn.bdqn.jboa.entity.Employee;

@Service
public class EmployeeBizImpl implements EmployeeBiz{

	@Autowired
	private EmployeeDao employeeDao;
	
	public Employee getEmployeeBySn(String sn) {
		return employeeDao.getEmployeeBySn(sn);
	}

	public Employee getEmployeeByDepartmentAndPosition(String departmentName,
			String positionName) {
		return employeeDao.getEmployeeByDepartmentAndPosition(departmentName, positionName);
	}

}
