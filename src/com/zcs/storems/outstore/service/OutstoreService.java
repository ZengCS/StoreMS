package com.zcs.storems.outstore.service;

import java.util.List;

import com.zcs.storems.instore.domain.Instore;
import com.zcs.storems.outstore.domain.Outstore;
import com.zcs.storems.util.Pagination;

public interface OutstoreService {
	public Pagination getPagination(Pagination pagination);
	
	public List<Outstore> statisticalByParam(Object[] param, String type);

	public List<Outstore> findAll();

	public Outstore findById(String id);

	public void save(Outstore outstore);

	public void update(Outstore outstore);

	public void delete(String id);

	public void batchDelete(List<String> ids);
	
	public Pagination getPagination(Object[] params, Pagination pagination);
}
