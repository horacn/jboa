package cn.bdqn.jboa.constans;

public class Constants {

	/**
	 * 登录成功后，session里面的员工对象的键
	 */
	public static final String SESSION_EMPLOYEE = "employee";
	public static final String ALL = "全部";
	public static final String BACK = "打回";
	public static final String PASS = "通过";
	public static final String REFUSE = "拒绝";
	public static final String PAYMENT = "付款";
	public static final int MONEY = 5000;
	

	// ---------------------------------员工状态---------------------------------
	/**
	 * 在职
	 */
	public static final String EMPLOYEE_STAY = "在职";
	/**
	 * 离职
	 */
	public static final String EMPLOYEE_LEAVE = "离职";
	/**
	 * 休假
	 */
	public static final String EMPLOYEE_VACATION = "休假";

	// ---------------------------------报销单状态---------------------------------
	/**
	 * 新创建
	 */
	public static final String VOUCHER_CREATED = "新创建";
	/**
	 * 已提交
	 */
	public static final String VOUCHER_SUBMITTED = "已提交";
	/**
	 * 已打回
	 */
	public static final String VOUCHER_BACK = "已打回";
	/**
	 * 待审批
	 */
	public static final String VOUCHER_WAIT = "待审批";
	/**
	 * 已审批
	 */
	public static final String VOUCHER_APPROVED = "已审批";
	/**
	 * 已付款
	 */
	public static final String VOUCHER_PAID = "已付款";
	/**
	 * 已终止
	 */
	public static final String VOUCHER_TERMINATED = "已终止";

	// ---------------------------------报销详细单费用类别---------------------------------
	/**
	 * 城际交通费
	 */
	public static final String VOUCHERDETAIL_CITYWALLTRAFFIC = "城际交通费";
	/**
	 * 市内交通费
	 */
	public static final String VOUCHERDETAIL_CITYTRAFFIC = "市内交通费";
	/**
	 * 通讯费
	 */
	public static final String VOUCHERDETAIL_COMMUNICATION = "通讯费";
	/**
	 * 礼品费
	 */
	public static final String VOUCHERDETAIL_GIFT = "礼品费";
	/**
	 * 办公费
	 */
	public static final String VOUCHERDETAIL_WORK = "办公费";
	/**
	 * 交际餐费
	 */
	public static final String VOUCHERDETAIL_SOCIETYMEAL = "交际餐费";
	/**
	 * 餐补
	 */
	public static final String VOUCHERDETAIL_MEAL = "餐补";
	/**
	 * 住宿费
	 */
	public static final String VOUCHERDETAIL_HOUSE = "住宿费";

	// ---------------------------------报销单审核结果---------------------------------
	/**
	 * 打回
	 */
	public static final String RESULT_BACK = "打回";
	/**
	 * 拒绝
	 */
	public static final String RESULT_REJECT = "拒绝";
	/**
	 * 通过
	 */
	public static final String RESULT_PASS = "通过";
	/**
	 * 付款
	 */
	public static final String RESULT_PAID = "付款";

	// ---------------------------------报销单处理类型---------------------------------
	/**
	 * 部门经理审核
	 */
	public static final String CHECKR_FM = "部门经理审核";
	/**
	 * 总经理审核
	 */
	public static final String CHECKR_GM = "总经理审核";
	/**
	 * 财务审核
	 */
	public static final String CHECKR_CASHIER = "财务审核";

	// ---------------------------------职务---------------------------------
	/**
	 * 员工
	 */
	public static final String POSITION_CN_STAFF = "员工";
	/**
	 * staff
	 */
	public static final String POSITION_EN_STAFF = "staff";
	/**
	 * 部门经理
	 */
	public static final String POSITION_CN_FM = "部门经理";
	/**
	 * manager
	 */
	public static final String POSITION_EN_FM = "manager";
	/**
	 * 总经理
	 */
	public static final String POSITION_CN_GM = "总经理";
	/**
	 * generalmanager
	 */
	public static final String POSITION_EN_GM = "generalmanager";
	/**
	 * 财务
	 */
	public static final String POSITION_CN_CASHIER = "财务";
	/**
	 * cashier
	 */
	public static final String POSITION_EN_CASHIER = "cashier";

	// ---------------------------------部门---------------------------------
	/**
	 * 人事部
	 */
	public static final String DEPARTMENT_PERSONNEL = "人事部";
	/**
	 * 平台研发部
	 */
	public static final String DEPARTMENT_STUDY = "平台研发部";
	/**
	 * 产品设计部
	 */
	public static final String DEPARTMENT_DESIGN = "产品设计部";
	/**
	 * 财务部
	 */
	public static final String DEPARTMENT_FINANCE = "财务部";
	/**
	 * 总裁办公室
	 */
	public static final String DEPARTMENT_PRESIDENT = "总裁办公室";

	// ---------------------------------请假类型---------------------------------
	/**
	 * 年假
	 */
	public static final String LEAVE_YEAR = "年假";
	/**
	 * 事假
	 */
	public static final String LEAVE_THING = "事假";
	/**
	 * 病假
	 */
	public static final String LEAVE_ILLNESS = "病假";
	/**
	 * 婚假
	 */
	public static final String LEAVE_MARRY = "婚假";
	/**
	 * 产假
	 */
	public static final String LEAVE_LAY = "产假";
	/**
	 * 丧假
	 */
	public static final String LEAVE_FUNERAL = "丧假";
	/**
	 * 休假
	 */
	public static final String LEAVE_REST = "休假";

	// ---------------------------------请假申请状态---------------------------------
	/**
	 * 已打回
	 */
	public static final String LEAVE_STATUS_BACK = "已打回";
	/**
	 * 待审批
	 */
	public static final String LEAVE_STATUS_WAIT = "待审批";
	/**
	 * 已审批
	 */
	public static final String LEAVE_STATUS_APPROVED = "已审批";

}
