package com.hailiang.common.consts;

/**
 * 
 *  mq监控程序相关常量
 */
public class MqMonitorConst {
	
	/**
	 * 监控队列标识
	 */
	public static final  String FF_MQ_MONITOR = "ff_mq_monitor_";
	
	/**
	 * 绑卡队列
	 */
	public static final String MQ_INVESTOR_INVEST_BINDCARD = "investor_invest_bindcard";

	/**
	 * 工单队列
	 */
	public static final String MQ_INVESTOR_INVEST_WORKORDER = "investor_invest_workorder";

	/**
	 * 投资管理 - 预处理队列
	 */
	public static final String MQ_INVEST_MANAGE_FW_ADVENCE = "invest_manage_fw_advance";
	
	/**
	 * 投资管理 - 支付队列
	 */
	public static final String MQ_INVEST_MANAGE_FW_PAY = "invest_manage_fw_pay";
	
	/**
	 * 投资管理 - 支付队列
	 */
	public static final String MQ_INVEST_MANAGE_FW_PAYERROR = "invest_manage_fw_payerror";

	
	/**
	 * 投资管理 - 投资反查队列
	 */
	public static final String MQ_INVEST_MANAGE_FW_BACKSEARCH = "invest_manage_fw_backsearch";
		
	/**
	 * 记账管理 - 余额账户记账队列
	 */
	public static final String MQ_ACCOUNTING_MANAGE_BAL = "accounting_manage_bal";
	
	/**
	 * 记账管理 - 奖金账户记账队列
	 */
	public static final String MQ_ACCOUNTING_MANAGE_REWARD = "accounting_manage_reward";

	
	/**
	 * 债权匹配队列
	 */
	public static final String MQ_FW_ASSETS_MANAGE_BORROW = "fw_assets_manage_borrow";

	/**
	 * 投资记录队列
	 */
	public static final String MQ_FW_INVEST_MANAGE_INVESTRECORD = "fw_invest_manage_investrecord";

}
