package com.zcs.storems.admin.service;

import java.util.List;

import com.zcs.storems.admin.domain.Account;
import com.zcs.storems.util.Pagination;

public interface AccountService {
	public Account login(Account account);

	public Pagination getPagination(Pagination pagination);

	public List<Account> findAll();

	public Account findById(String id);

	public void save(Account account);

	public void update(Account account);

	public void delete(String id);

	public void batchDelete(List<String> ids);

	public Pagination getPagination(Object[] params, Pagination pagination);
}
