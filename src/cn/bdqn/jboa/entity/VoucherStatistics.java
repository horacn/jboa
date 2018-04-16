package cn.bdqn.jboa.entity;

import java.util.Date;

/**
 * 报销统计
 * @author MyEclipse Persistence Tools
 */

public class VoucherStatistics implements java.io.Serializable {

	// Fields

	private Integer id;//编号
	private Department department;//所属部门
	private Double totalCount;//总金额
	private Integer year;//年份
	private Integer month;//月份
	private Date modifyTime;//最近一次修改时间

	// Constructors

	/** default constructor */
	public VoucherStatistics() {
	}

	/** full constructor */
	public VoucherStatistics(Department department, Double totalCount,
			Integer year, Integer month, Date modifyTime) {
		this.department = department;
		this.totalCount = totalCount;
		this.year = year;
		this.month = month;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Double getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Double totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}