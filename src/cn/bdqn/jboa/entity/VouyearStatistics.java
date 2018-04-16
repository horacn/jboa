package cn.bdqn.jboa.entity;

import java.util.Date;

/**
 * 报销年度统计
 * @author MyEclipse Persistence Tools
 */

public class VouyearStatistics implements java.io.Serializable {

	// Fields

	private Integer id;//编号
	private Double totalCount;//总金额
	private Integer year;//年份
	private Date modifyTime;//最近一次修改时间
	private Department department;//部门编号

	// Constructors

	/** default constructor */
	public VouyearStatistics() {
	}

	/** full constructor */
	public VouyearStatistics(Double totalCount, Integer year, Date modifyTime,
			Department department) {
		this.totalCount = totalCount;
		this.year = year;
		this.modifyTime = modifyTime;
		this.department = department;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}