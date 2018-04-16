package cn.bdqn.jboa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.constans.Constants;
import cn.bdqn.jboa.dao.LeaveDao;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Leave;

@Repository
public class LeaveDaoImpl extends HibernateDaoSupport implements LeaveDao {

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 查看员工请假列表
	 * 
	 * @param leavetype
	 * @param beginDate
	 * @param endDate
	 * @param employee
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageBean<Leave> search(String status,String leavetype, String beginDate,
			String endDate, Employee employee, Integer pageIndex,
			Integer pageSize) {
		StringBuilder hql = new StringBuilder("from Leave where 1=1 ");
		// 查询条件集合
		Map<String, Object> params = new HashMap<String, Object>();
		String positionNameCn = employee.getPosition().getNameCn();
		// 如果是员工，就只能查看自己的报销单
		if (positionNameCn.equals(Constants.POSITION_CN_STAFF)) {
			hql.append(" and employee.sn=:sn ");
		} else if (positionNameCn.equals(Constants.POSITION_CN_FM)) {
			// 如果是部门经理，查看本部门员工提交的状态为“待审批”的请假申请
			hql.append(" and nextDeal.sn=:sn ");
		}
		params.put("sn", employee.getSn());
		if (status != null && status.length() > 0
				&& !status.equals(Constants.ALL)) {
			hql.append(" and status=:status ");
			params.put("status", status);
		}
		if (leavetype != null && leavetype.length() > 0
				&& !leavetype.equals(Constants.ALL)) {
			hql.append(" and leavetype=:leavetype ");
			params.put("leavetype", leavetype);
		}
		if (beginDate != null && beginDate.length() > 0) {
			hql.append(" and createtime>=to_date(:beginDate,'yyyy-mm-dd') ");
			params.put("beginDate", beginDate);
		}
		if (endDate != null && endDate.length() > 0) {
			hql.append(" and createtime<=to_date(:endDate,'yyyy-mm-dd') ");
			params.put("endDate", endDate);
		}
		// 按状态升序、日期降序排列
		hql.append(" order by status,createTime desc");
		Session session = this.getSession();
		Query query = session.createQuery(hql.toString());
		query.setProperties(params);
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Leave> list = query.list();
		int totalCount = getTotalCount(status,leavetype, beginDate, endDate, employee);
		PageBean<Leave> pageBean = new PageBean<Leave>(list, pageSize,
				pageIndex, totalCount);
		return pageBean;
	}

	/**
	 * 获得总数量
	 * 
	 * @param leavetype
	 * @param beginDate
	 * @param endDate
	 * @param employee
	 * @return
	 */
	public int getTotalCount(String status,String leavetype, String beginDate,
			String endDate, Employee employee) {
		StringBuilder hql = new StringBuilder("select count(*) from Leave where 1=1 ");
		// 查询条件集合
		Map<String, Object> params = new HashMap<String, Object>();
		String positionNameCn = employee.getPosition().getNameCn();
		// 如果是员工，就只能查看自己的报销单
		if (positionNameCn.equals(Constants.POSITION_CN_STAFF)) {
			hql.append(" and employee.sn=:sn ");
		} else if (positionNameCn.equals(Constants.POSITION_CN_FM)) {
			// 如果是部门经理，查看本部门员工提交的状态为“待审批”的请假申请
			hql.append(" and nextDeal.sn=:sn ");
		}
		params.put("sn", employee.getSn());
		if (status != null && status.length() > 0
				&& !status.equals(Constants.ALL)) {
			hql.append(" and status=:status ");
			params.put("status", status);
		}
		if (leavetype != null && leavetype.length() > 0
				&& !leavetype.equals(Constants.ALL)) {
			hql.append(" and leavetype=:leavetype ");
			params.put("leavetype", leavetype);
		}
		if (beginDate != null && beginDate.length() > 0) {
			hql.append(" and createtime>=to_date(:beginDate,'yyyy-mm-dd') ");
			params.put("beginDate", beginDate);
		}
		if (endDate != null && endDate.length() > 0) {
			hql.append(" and createtime<=to_date(:endDate,'yyyy-mm-dd') ");
			params.put("endDate", endDate);
		}
		// 按状态升序、日期降序排列
		hql.append(" order by status,createTime desc");
		Session session = this.getSession();
		Query query = session.createQuery(hql.toString());
		query.setProperties(params);
		long totalCunt = (Long) query.uniqueResult();
		return (int) totalCunt;
	}

	public Leave get(Integer id) {
		return getHibernateTemplate().get(Leave.class, id);
	}

	public void saveOrUpdate(Leave leave) {
		getHibernateTemplate().saveOrUpdate(leave);
	}
	
	public void delete(Integer id) {
		Leave leave = this.get(id);
		getHibernateTemplate().delete(leave);
	}

}
