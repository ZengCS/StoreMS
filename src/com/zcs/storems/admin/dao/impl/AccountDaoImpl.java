package com.zcs.storems.admin.dao.impl;

import com.zcs.storems.admin.dao.AccountDao;
import com.zcs.storems.admin.domain.Account;
import com.zcs.storems.util.GenericDaoImpl;
import org.hibernate.Session;
import org.hibernate.Query;

public class AccountDaoImpl extends GenericDaoImpl<Account, String> implements AccountDao {

	// 获取用户登录信息
	public Account login(Account account) {
		Session session = getSession();
		Query query = session.createQuery("from Account a where a.userName=:user and a.password=:pwd");
		query.setString("user", account.getUserName());
		query.setString("pwd", account.getPassword());

		// 如果这里没有找到记录,则account的值会变为null
		account = (Account) query.uniqueResult();
		return account;
	}
}
