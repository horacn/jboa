package cn.bdqn.jboa.entity;

import java.util.Date;

/**
 * 审批结果
 * 
 * @author MyEclipse Persistence Tools
 */

public class Result implements java.io.Serializable {

	// Fields

	private Integer id;// 审批结果编号
	private Voucher voucher;// 所属报销单
	private Date checkTime;// 审核时间
	private Employee checkEmployee;// 审批人
	private String result;// 审批结果:通过、打回、拒绝、付款
	private String comm;// 备注

	// Constructors

	/** default constructor */
	public Result() {
	}

	/** full constructor */
	public Result(Voucher voucher, Date checkTime, Employee checkEmployee,
			String result, String comm) {
		this.voucher = voucher;
		this.checkTime = checkTime;
		this.checkEmployee = checkEmployee;
		this.result = result;
		this.comm = comm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Voucher getVoucher() {
		return this.voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Employee getCheckEmployee() {
		return checkEmployee;
	}

	public void setCheckEmployee(Employee checkEmployee) {
		this.checkEmployee = checkEmployee;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

}