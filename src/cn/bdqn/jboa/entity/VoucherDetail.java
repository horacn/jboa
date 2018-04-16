package cn.bdqn.jboa.entity;

/**
 * 报销详细项
 * @author MyEclipse Persistence Tools
 */

public class VoucherDetail implements java.io.Serializable {

	// Fields

	private Integer id;//编号
	private Voucher voucher;//所属报销单
	private String item;//费用类别
	private Double account;//金额
	private String description;//描述

	// Constructors

	/** default constructor */
	public VoucherDetail() {
	}

	/** full constructor */
	public VoucherDetail(Voucher voucher, String item, Double account,
			String description) {
		this.voucher = voucher;
		this.item = item;
		this.account = account;
		this.description = description;
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

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getAccount() {
		return this.account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}