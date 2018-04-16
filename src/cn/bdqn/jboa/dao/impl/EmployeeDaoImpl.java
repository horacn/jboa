package cn.bdqn.jboa.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.bdqn.jboa.dao.EmployeeDao;
import cn.bdqn.jboa.entity.Employee;

@Repository
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao{

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public Employee getEmployeeBySn(String sn) {
		return getHibernateTemplate().get(Employee.class, sn);
	}

	public Employee getEmployeeByDepartmentAndPosition(String departmentName,
			String positionName) {
		String hql = "from Employee where department.name=? and position.nameCn=?";
		getHibernateTemplate().setMaxResults(1);
		return (Employee) getHibernateTemplate().find(hql, departmentName,
				positionName).get(0);
	}

}
