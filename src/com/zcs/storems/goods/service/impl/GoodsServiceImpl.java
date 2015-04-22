package com.zcs.storems.goods.service.impl;

import java.util.List;

import com.zcs.storems.goods.dao.GoodsDao;
import com.zcs.storems.goods.domain.Goods;
import com.zcs.storems.goods.service.GoodsService;
import com.zcs.storems.util.Pagination;
import com.zcs.storems.util.ResultType;

public class GoodsServiceImpl implements GoodsService {
	private GoodsDao goodsDao;

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	@Override
	public Pagination getPagination(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAll() {
		return goodsDao.findAll();
	}

	@Override
	public Goods findById(String id) {
		return goodsDao.findById(id);
	}

	@Override
	public void save(Goods goods) {
		goodsDao.save(goods);
	}

	@Override
	public void update(Goods goods) {
		goodsDao.update(goods);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(List<String> ids) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Goods a where a.id in (");
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
		goodsDao.findByHql(ResultType.NO_RESULT, sb.toString(), params);
	}
	
	@Override
	public Pagination getPagination(Object[] params, Pagination pagination) {
		String hql = "select count(*) from Goods";
		String hql2 = "from Goods order by ? ";
		pagination.setResultCount(Integer.parseInt(goodsDao.countByHql(hql, null).toString()));
		return goodsDao.getPagination(hql2, params, pagination);
	}
}
