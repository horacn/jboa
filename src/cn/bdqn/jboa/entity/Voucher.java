package cn.bdqn.jboa.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 报销单
 * @author MyEclipse Persistence Tools
 */

public class Voucher implements java.io.Serializable {

	// Fields

	private Integer id;//报销单编号
	private Employee employeeByNextDealSn;//下一个审批人
	private Employee employeeByCreateSn;//报销单提交人
	private Date createTime;//创建时间
	private String event;//事件
	private Double totalAccount;//总金额
	private String status;//状态：新创建、已提交、待审批、已打回、已审批、已付款、已终止
	private Date modifyTime;//最近一次修改时间
	private Set<Result> results = new HashSet<Result>(0);//审批结果集合
	private Set<VoucherDetail> voucherDetails = new HashSet<VoucherDetail>(0);//报销详细项集合

	// Constructors

	/** default constructor */
	public Voucher() {
	}

	/** minimal constructor */
	public Voucher(Employee employeeByCreateSn, Date createTime, String event,
			Double totalAccount, String status) {
		this.employeeByCreateSn = employeeByCreateSn;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
	}

	/** full constructor */
	public Voucher(Employee employeeByNextDealSn, Employee employeeByCreateSn,
			Date createTime, String event, Double totalAccount, String status,
			Date modifyTime, Set<Result> results, Set<VoucherDetail> voucherDetails) {
		this.employeeByNextDealSn = employeeByNextDealSn;
		this.employeeByCreateSn = employeeByCreateSn;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
		this.modifyTime = modifyTime;
		this.results = results;
		this.voucherDetails = voucherDetails;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployeeByNextDealSn() {
		return this.employeeByNextDealSn;
	}

	public void setEmployeeByNextDealSn(Employee employeeByNextDealSn) {
		this.employeeByNextDealSn = employeeByNextDealSn;
	}

	public Employee getEmployeeByCreateSn() {
		return this.employeeByCreateSn;
	}

	public void setEmployeeByCreateSn(Employee employeeByCreateSn) {
		this.employeeByCreateSn = employeeByCreateSn;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Double getTotalAccount() {
		return this.totalAccount;
	}

	public void setTotalAccount(Double totalAccount) {
		this.totalAccount = totalAccount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Set<Result> getResults() {
		return this.results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	public Set<VoucherDetail> getVoucherDetails() {
		return this.voucherDetails;
	}

	public void setVoucherDetails(Set<VoucherDetail> voucherDetails) {
		this.voucherDetails = voucherDetails;
	}

}