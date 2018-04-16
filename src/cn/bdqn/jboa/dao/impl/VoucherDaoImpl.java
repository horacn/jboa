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
import cn.bdqn.jboa.dao.VoucherDao;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Voucher;

@Repository
public class VoucherDaoImpl extends HibernateDaoSupport implements VoucherDao {

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 搜索报销单
	 * 
	 * @param status
	 * @param beginDate
	 * @param endDate
	 * @param employee
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageBean<Voucher> search(String status, String beginDate,
			String endDate, Employee employee, Integer pageIndex,
			Integer pageSize) {
		StringBuilder hql = new StringBuilder("from Voucher where 1=1 ");
		// 查询条件集合
		Map<String, Object> params = new HashMap<String, Object>();
		String positionNameCn = employee.getPosition().getNameCn();
		// 如果是员工，就只能查看自己的报销单
		if (positionNameCn.equals(Constants.POSITION_CN_STAFF)) {
			hql.append(" and employeeByCreateSn.sn=:sn ");
			params.put("sn", employee.getSn());
		} else if (positionNameCn.equals(Constants.POSITION_CN_FM)) {
			// 如果是部门经理，查询出自己部门员工填报的所有报销单，不显示状态为“新创建”的单据
			hql.append(" and employeeByCreateSn.department.name=:departmentName and status!= '"
					+ Constants.VOUCHER_CREATED + "' ");
			params.put("departmentName", employee.getDepartment().getName());
		} else {
			// 总经理或财务：待处理人为当前登录用户,或者当前登录用户曾审批过
			hql.append(" and(employeeByNextDealSn.sn=:sn or id in(select voucher.id from Result where checkEmployee.sn=:sn)) ");
			params.put("sn", employee.getSn());
		}
		if (status != null && status.length() > 0
				&& !status.equals(Constants.ALL)) {
			hql.append(" and status=:status ");
			params.put("status", status);
		}
		if (beginDate != null && beginDate.length() > 0) {
			hql.append(" and createTime>=to_date(:beginDate,'yyyy-mm-dd') ");
			params.put("beginDate", beginDate);
		}
		if (endDate != null && endDate.length() > 0) {
			hql.append(" and createTime<=to_date(:endDate,'yyyy-mm-dd') ");
			params.put("endDate", endDate);
		}
		// 按状态升序、日期降序排列
		hql.append(" order by status,createTime desc");
		Session session = this.getSession();
		Query query = session.createQuery(hql.toString());
		query.setProperties(params);
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Voucher> list = query.list();
		int totalCount = getTotalCount(status, beginDate, endDate, employee);
		PageBean<Voucher> pageBean = new PageBean<Voucher>(list, pageSize,
				pageIndex, totalCount);
		return pageBean;
	}

	/**
	 * 获得总数量
	 * 
	 * @param status
	 * @param beginDate
	 * @param endDate
	 * @param employee
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public int getTotalCount(String status, String beginDate, String endDate,
			Employee employee) {
		StringBuilder hql = new StringBuilder(
				"select count(*) from Voucher where 1=1 ");
		// 查询条件集合
		Map<String, Object> params = new HashMap<String, Object>();
		String positionNameCn = employee.getPosition().getNameCn();
		// 如果是员工，就只能查看自己的报销单
		if (positionNameCn.equals(Constants.POSITION_CN_STAFF)) {
			hql.append(" and employeeByCreateSn.sn=:sn ");
			params.put("sn", employee.getSn());
		} else if (positionNameCn.equals(Constants.POSITION_CN_FM)) {
			// 如果是部门经理，查询出自己部门员工填报的所有报销单，不显示状态为“新创建”的单据
			hql.append(" and employeeByCreateSn.department.name=:departmentName and status!= '"
					+ Constants.VOUCHER_CREATED + "' ");
			params.put("departmentName", employee.getDepartment().getName());
		} else {
			// 总经理或财务：待处理人为当前登录用户,或者当前登录用户曾审批过
			hql.append(" and(employeeByNextDealSn.sn=:sn or id in(select voucher.id from Result where checkEmployee.sn=:sn)) ");
			params.put("sn", employee.getSn());
		}
		if (status != null && status.length() > 0
				&& !status.equals(Constants.ALL)) {
			hql.append(" and status=:status ");
			params.put("status", status);
		}
		if (beginDate != null && beginDate.length() > 0) {
			hql.append(" and createTime>=to_date(:beginDate,'yyyy-mm-dd') ");
			params.put("beginDate", beginDate);
		}
		if (endDate != null && endDate.length() > 0) {
			hql.append(" and createTime<=to_date(:endDate,'yyyy-mm-dd') ");
			params.put("endDate", endDate);
		}
		Session session = this.getSession();
		Query query = session.createQuery(hql.toString());
		query.setProperties(params);
		long totalCunt = (Long) query.uniqueResult();
		return (int) totalCunt;
	}

	public void delete(Integer id) {
		Voucher voucher = this.get(id);
		getHibernateTemplate().delete(voucher);
	}

	public Voucher get(Integer id) {
		return getHibernateTemplate().get(Voucher.class, id);
	}

	public void saveOrUpdate(Voucher voucher) {
		getHibernateTemplate().saveOrUpdate(voucher);
	}

}
