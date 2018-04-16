package cn.bdqn.jboa.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bdqn.jboa.biz.LeaveBiz;
import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.dao.LeaveDao;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Leave;

@Service
public class LeaveBizImpl implements LeaveBiz {

	@Autowired
	private LeaveDao leaveDao;
	
	public PageBean<Leave> search(String status,String leavetype, String beginDate,
			String endDate, Employee employee, Integer pageIndex,
			Integer pageSize) {
		return leaveDao.search(status,leavetype, beginDate, endDate, employee, pageIndex, pageSize);
	}

	public int getTotalCount(String status,String leavetype, String beginDate,
			String endDate, Employee employee) {
		return leaveDao.getTotalCount(status,leavetype, beginDate, endDate, employee);
	}

	public Leave get(Integer id) {
		return leaveDao.get(id);
	}

	@Transactional
	public void saveOrUpdate(Leave leave) {
		leaveDao.saveOrUpdate(leave);
	}
	
	@Transactional
	public void delete(Integer id) {
		leaveDao.delete(id);
	}

}
