package cn.bdqn.jboa.dao;

import cn.bdqn.jboa.entity.Employee;

public interface EmployeeDao {
	/**
	 * 根据工号获取员工对象
	 * @param sn
	 * @return
	 */
	Employee getEmployeeBySn(String sn);
	
	/**
	 * 根据部门名称及职位名称获得员工对象
	 * @return
	 */
	Employee getEmployeeByDepartmentAndPosition(String departmentName,String positionName);
}
