package com.zcs.storems.atstore.service;

import java.util.List;

import com.zcs.storems.atstore.domain.Atstore;
import com.zcs.storems.util.Pagination;

public interface AtstoreService {
	public Pagination getPagination(Pagination pagination);

	public List<Atstore> findAll();

	public Atstore findById(String id);

	public void save(Atstore atstore);

	public void update(Atstore atstore);

	public void delete(String id);

	public void batchDelete(List<String> ids);

	public Atstore getByGoodsId(String goodsId);

	public Atstore getByRelationId(String relationId);
	
	public Pagination getPagination(Object[] params, Pagination pagination);
}
