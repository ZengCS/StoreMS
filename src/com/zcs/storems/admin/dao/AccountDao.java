package com.zcs.storems.admin.dao;

import com.zcs.storems.admin.domain.Account;
import com.zcs.storems.util.GenericDao;

public interface AccountDao extends GenericDao<Account, String> {
	public Account login(Account account);
}
