package cn.bdqn.jboa.biz;

import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Leave;

public interface LeaveBiz {

	/**
	 * 查看员工请假列表
	 * @param status
	 * @param leavetype
	 * @param beginDate
	 * @param endDate
	 * @param employee
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageBean<Leave> search(String status,String leavetype, String beginDate, String endDate,
			Employee employee, Integer pageIndex, Integer pageSize);

	/**
	 * 获得总数量
	 * @param status
	 * @param leavetype
	 * @param beginDate
	 * @param endDate
	 * @param employee
	 * @return
	 */
	int getTotalCount(String status,String leavetype, String beginDate, String endDate,
			Employee employee);

	Leave get(Integer id);

	void saveOrUpdate(Leave leave);
	
	void delete(Integer id);
}
