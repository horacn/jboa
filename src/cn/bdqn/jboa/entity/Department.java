package cn.bdqn.jboa.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门
 * @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private Integer id;//部门编号
	private String name;//部门名称:人事部,平台研发部,产品设计部,财务部,总裁办公室
	private Set<Employee> employees = new HashSet<Employee>(0);//该部门员工集合
	private Set<VoucherStatistics> voucherStatisticses = new HashSet<VoucherStatistics>(0);//该部门报销统计集合

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String name) {
		this.name = name;
	}

	/** full constructor */
	public Department(String name, Set<Employee> employees, Set<VoucherStatistics> voucherStatisticses) {
		this.name = name;
		this.employees = employees;
		this.voucherStatisticses = voucherStatisticses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<VoucherStatistics> getVoucherStatisticses() {
		return this.voucherStatisticses;
	}

	public void setVoucherStatisticses(Set<VoucherStatistics> voucherStatisticses) {
		this.voucherStatisticses = voucherStatisticses;
	}

}