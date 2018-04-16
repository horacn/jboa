package cn.bdqn.jboa.biz;

import cn.bdqn.jboa.entity.Employee;

public interface EmployeeBiz {
	Employee getEmployeeBySn(String sn);
	
	/**
	 * 根据部门名称及职位名称获得员工对象
	 * @return
	 */
	Employee getEmployeeByDepartmentAndPosition(String departmentName,String positionName);
}
