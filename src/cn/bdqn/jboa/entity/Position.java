package cn.bdqn.jboa.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 职位
 * @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

	// Fields

	private Integer id;//职位编号
	private String nameCn;//中文职位名称：员工、部门经理、总经理、财务
	private String nameEn;//英文职位名称
	private Set<Employee> employees = new HashSet<Employee>(0);//该职位的员工集合

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** minimal constructor */
	public Position(String nameCn, String nameEn) {
		this.nameCn = nameCn;
		this.nameEn = nameEn;
	}

	/** full constructor */
	public Position(String nameCn, String nameEn, Set<Employee> employees) {
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.employees = employees;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}