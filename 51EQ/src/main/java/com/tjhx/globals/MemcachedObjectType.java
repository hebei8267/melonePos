package com.tjhx.globals;

/**
 * 统一定义Memcached中存储的各种对象Key和超时时间.
 */
public enum MemcachedObjectType {
	/** 供应商列表 */
	SUPPLIER_LIST("Supplier_List", 60 * 60 * 24), // 24小时
	/** 银行列表 */
	BANK_LIST("Bank_List", 60 * 60 * 24),
	/** 银行卡Map */
	BANK_CARD_MAP("Bank_Card_Map", 60 * 60 * 24), // 24小时
	/** 机构列表 */
	ORG_LIST("Org_List", 60 * 60 * 24), // 24小时
	/** 角色列表 */
	ROLE_LIST("Role_List", 60 * 60 * 24), // 24小时
	/** 区域列表 */
	REGION_LIST("Region_List", 60 * 60 * 24), // 24小时
	/** 功能列表 */
	FUN_LIST("Fun_List", 60 * 60 * 24), // 24小时
	/** 功能列表 */
	USER_LIST("User_List", 60 * 60 * 24), // 24小时
	/** 商品种类列表 */
	ITEM_TYPE_LIST("Item_Type_List", 60 * 60 * 24), // 24小时
	/** 代金卷种类列表 */
	COUPON_TYPE_LIST("Coupon_Type_List", 60 * 60 * 24), // 24小时
	/** 月销售目标 */
	MONTH_SALE_TARGET_MAP("Month_Sale_Target_Map", 60 * 60 * 24), // 24小时
	;

	/** Memcached对象Key */
	private String objKey;
	/** 超时时间 */
	private int expiredTime;

	MemcachedObjectType(String objKey, int expiredTime) {
		this.objKey = objKey;
		this.expiredTime = expiredTime;
	}

	/**
	 * 取得Memcached对象Key
	 * 
	 * @return Memcached对象Key
	 */
	public String getObjKey() {
		return objKey;
	}

	/**
	 * 取得超时时间
	 * 
	 * @return 超时时间
	 */
	public int getExpiredTime() {
		return expiredTime;
	}

}
