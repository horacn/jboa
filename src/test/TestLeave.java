package test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.dao.LeaveDao;
import cn.bdqn.jboa.dao.impl.EmployeeDaoImpl;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Leave;

public class TestLeave {
	
	@Test
	public void test1() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		LeaveDao leaveDao =  (LeaveDao) context.getBean("leaveDaoImpl");
		EmployeeDaoImpl employeeDaoImpl =  (EmployeeDaoImpl) context.getBean("employeeDaoImpl");
		Employee employee = employeeDaoImpl.getEmployeeBySn("001");
		PageBean<Leave> pageBean = leaveDao.search(null, null, null, employee, 1, 5);
		for (Leave l : pageBean.getList()) {
			System.out.println(l.getEmployee().getName());
			System.out.println(l.getNextDeal().getName());
			System.out.println("-------");
		}
	}

}
