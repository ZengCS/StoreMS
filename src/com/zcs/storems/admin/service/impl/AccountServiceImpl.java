package com.zcs.storems.admin.service.impl;

import java.util.List;

import com.zcs.storems.admin.dao.AccountDao;
import com.zcs.storems.admin.domain.Account;
import com.zcs.storems.admin.service.AccountService;
import com.zcs.storems.util.Pagination;
import com.zcs.storems.util.ResultType;
import com.zcs.util.MD5;

public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Account login(Account account) {
		System.out.println("执行登录");
		System.out.println(account.getUserName());
		System.out.println(account.getPassword());
		account.setUserName(account.getUserName().toLowerCase());
		account.setPassword(MD5.getMd5(account.getPassword()));
		return accountDao.login(account);
	}

	@Override
	public Pagination getPagination(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

	@Override
	public Account findById(String id) {
		return accountDao.findById(id);
	}

	@Override
	public void save(Account account) {
		accountDao.save(account);
	}

	@Override
	public void update(Account account) {
		accountDao.update(account);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(List<String> ids) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Account a where a.id in (");
		Object[] params = new Object[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			params[i] = ids.get(i);
			if (i == ids.size() - 1) {
				sb.append("?)");
			} else {
				sb.append("?,");
			}
		}
		System.out.println(sb.toString());
		accountDao.findByHql(ResultType.NO_RESULT, sb.toString(), params);
	}

	@Override
	public Pagination getPagination(Object[] params, Pagination pagination) {
		String hql = "select count(*) from Account";
		String hql2 = "from Account order by ? ";
		pagination.setResultCount(Integer.parseInt(accountDao.countByHql(hql, null).toString()));
		return accountDao.getPagination(hql2, params, pagination);
	}
}
