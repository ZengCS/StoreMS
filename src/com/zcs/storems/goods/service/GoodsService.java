package com.zcs.storems.goods.service;

import java.util.List;

import com.zcs.storems.goods.domain.Goods;
import com.zcs.storems.util.Pagination;

public interface GoodsService {
	public Pagination getPagination(Pagination pagination);

	public List<Goods> findAll();

	public Goods findById(String id);

	public void save(Goods goods);

	public void update(Goods goods);

	public void delete(String id);

	public void batchDelete(List<String> ids);
	
	public Pagination getPagination(Object[] params, Pagination pagination);
}
