package com.tjhx.dao.accounts;

import java.util.List;

import com.tjhx.entity.accounts.PrePayments;


public interface PrePaymentsMyBatisDao {

	public List<PrePayments> getPrePaymentsList(PrePayments prePayments);

}
