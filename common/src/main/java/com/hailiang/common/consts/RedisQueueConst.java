package com.hailiang.common.consts;

/**
 * redis 队列名称  常量类
 *
 */
public class RedisQueueConst {
	
	/**************************************** 债权  **********************************************/
	/** 债权管理消息队列key */
	public final static String REDIS_QUEUE_BORROW_MANAGE_LIST = "redis_queue_borrow_manage_list";
	
	/** 债权管理消息备份队列key */
	public final static String REDIS_QUEUE_BORROW_MANAGE_BACKUP_MAP_KEY = "redis_queue_borrow_manage_backup_map_key";
	
    /**************************************** 投资相关  ***************************************/
		
	/** 投资工单消息队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_WORKORDER_LIST = "redis_queue_fw_invest_workorder_list";
	
	/** 投资工单消息备份队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_WORKORDER_BACKUP_MAP_KEY = "redis_queue_fw_invest_workorder_backup_map_key";
	
	/** 投资绑卡消息队列key*/
	public final static String REDIS_QUEUE_INVEST_BINDCARD_LIST = "queue_invest_bindcard_list";
	
	/** 投资绑卡消息备份队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_BINDCARD_BACKUP_MAP_KEY = "queue_invest_bindcard_backup_map_key";
	
	/** 投资预处理消息队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_ADVANCE_LIST = "redis_queue_fw_invest_advance_list";
	
	/** 投资支付消息备份队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_ADVANCE_BACKUP_MAP_KEY = "redis_queue_fw_invest_advance_backup_map_key";
	
	/** 投资支付消息队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_PAY_LIST = "redis_queue_fw_invest_pay_list";
	
	/** 投资支付消息备份队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_PAY_BACKUP_MAP_KEY = "redis_queue_fw_invest_pay_backup_map_key";
	
	/** 投资反查消息队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_BACKSEARCH_LIST = "redis_queue_fw_invest_backsearch_list";
	
	/** 投资反查消息备份队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_BACKSEARCH_BACKUP_MAP_KEY = "redis_queue_fw_invest_backsearch_backup_map_key";
	
	/** 投资失败消息队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_PAYERROR_LIST = "redis_queue_fw_invest_payerror_list";
	
	/** 投资失败消息备份队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_PAYERROR_BACKUP_MAP_KEY = "redis_queue_fw_invest_payerror_backup_map_key";	
	
	/** 投资记录消息队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_RECORD_MANAGE_LIST = "queue_fw_invest_record_manage_list";
	
	/** 投资记录消息备份队列key*/
	public final static String REDIS_QUEUE_FW_INVEST_RECORD_MANAGE_BACKUP_MAP_KEY = "queue_fw_invest_record_manage_backup_map_key";
	
	
	/**************************************** 记账相关  *******************************************/
	
	/** 记账管理 - 余额账户记账消息队列key*/
	public final static String REDIS_QUEUE_ACCOUNT_BAL_LIST = "redis_queue_accounting_bal_list";
	
	/** 记账管理 - 余额账户记账消息备份MAP key*/
	public final static String REDIS_QUEUE_ACCOUNT_BAL_BACKUP_MAP_KEY = "redis_queue_accounting_bal_backup_map_key";
	
	/** 记账管理 - 奖金账户记账消息队列key*/
	public final static String REDIS_QUEUE_ACCOUNT_REWARD_LIST = "redis_queue_accounting_reward_list";
	
	/** 记账管理 - 奖金账户记账消息备份队列key*/
	public final static String REDIS_QUEUE_ACCOUNT_REWARD_BACKUP_MAP_KEY = "redis_queue_accounting_reward_backup_map_key";
	
	
	/** 记账管理 - 账户记账消息队列key*/
	public final static String REDIS_QUEUE_ACCOUNT_FW_LIST = "redis_queue_accounting_fw_list";
	
	/** 记账管理 - 账户记账消息备份队列key*/
	public final static String REDIS_QUEUE_ACCOUNT_FW_BACKUP_MAP_KEY = "redis_queue_accounting_fw_backup_map_key";
}