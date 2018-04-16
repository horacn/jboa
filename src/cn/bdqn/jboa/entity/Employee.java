package cn.bdqn.jboa.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 员工
 * @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private String sn;//工号
	private Position position;//职位
	private Department department;//所属部门
	private String name;//姓名
	private String password;//密码
	private String status;//当前状态
	private Set<Voucher> vouchersForNextDealSn = new HashSet<Voucher>(0);
	private Set<Voucher> vouchersForCreateSn = new HashSet<Voucher>(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String sn, Position position, Department department,
			String name, String password, String status) {
		this.sn = sn;
		this.position = position;
		this.department = department;
		this.name = name;
		this.password = password;
		this.status = status;
	}

	/** full constructor */
	public Employee(String sn, Position position, Department department,
			String name, String password, String status,
			Set<Voucher> vouchersForNextDealSn, Set<Voucher> vouchersForCreateSn) {
		this.sn = sn;
		this.position = position;
		this.department = department;
		this.name = name;
		this.password = password;
		this.status = status;
		this.vouchersForNextDealSn = vouchersForNextDealSn;
		this.vouchersForCreateSn = vouchersForCreateSn;
	}

	// Property accessors

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Voucher> getVouchersForNextDealSn() {
		return this.vouchersForNextDealSn;
	}

	public void setVouchersForNextDealSn(Set<Voucher> vouchersForNextDealSn) {
		this.vouchersForNextDealSn = vouchersForNextDealSn;
	}

	public Set<Voucher> getVouchersForCreateSn() {
		return this.vouchersForCreateSn;
	}

	public void setVouchersForCreateSn(Set<Voucher> vouchersForCreateSn) {
		this.vouchersForCreateSn = vouchersForCreateSn;
	}

}