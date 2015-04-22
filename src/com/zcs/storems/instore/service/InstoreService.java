package com.zcs.storems.instore.service;

import java.util.List;

import com.zcs.storems.instore.domain.Instore;
import com.zcs.storems.util.Pagination;

public interface InstoreService {
	public Pagination getPagination(Pagination pagination);

	public List<Instore> statisticalByParam(Object[] param, String type);

	public List<Instore> findAll();

	public Instore findById(String id);

	public void save(Instore instore);

	public void update(Instore instore);

	public void delete(String id);

	public void batchDelete(List<String> ids);

	public Pagination getPagination(Object[] params, Pagination pagination);
}
