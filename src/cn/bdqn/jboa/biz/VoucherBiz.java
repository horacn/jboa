package cn.bdqn.jboa.biz;

import org.springframework.transaction.annotation.Transactional;

import cn.bdqn.jboa.common.PageBean;
import cn.bdqn.jboa.entity.Employee;
import cn.bdqn.jboa.entity.Voucher;

public interface VoucherBiz {

	/**
	 * 搜索报销单
	 * @param status
	 * @param beginDate
	 * @param endDate
	 * @param employee
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public abstract PageBean<Voucher> search(String status, String beginDate,
			String endDate, Employee employee, Integer pageIndex,
			Integer pageSize);

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
	int getTotalCount(String status, String beginDate, String endDate,
			Employee employee);
	
	/**
	 * 添加或修改
	 * @param voucher
	 */
	@Transactional
	public abstract void saveOrUpdate(Voucher voucher);

	/**
	 * 删除
	 * @param id
	 */
	@Transactional
	public abstract void delete(Integer id);

	/**
	 * 根据主键获得
	 * @param id
	 * @return
	 */
	public abstract Voucher get(Integer id);
	
	/**
	 * 把报销单交给下个处理人
	 * @param id
	 * @return
	 */
	void nextCheckEmp(Voucher voucher,String positionName,String departmentName,String status);
	
	void nextCheckEmp(Voucher voucher,Employee nextDealEmp,String status);

}