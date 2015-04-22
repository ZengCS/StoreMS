package com.zcs.storems.atstore.service.impl;

import java.util.List;

import com.zcs.storems.atstore.dao.AtstoreDao;
import com.zcs.storems.atstore.domain.Atstore;
import com.zcs.storems.atstore.service.AtstoreService;
import com.zcs.storems.util.Pagination;
import com.zcs.storems.util.ResultType;

public class AtstoreServiceImpl implements AtstoreService {
	private AtstoreDao atstoreDao;

	public void setAtstoreDao(AtstoreDao atstoreDao) {
		this.atstoreDao = atstoreDao;
	}

	@Override
	public Pagination getPagination(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atstore> findAll() {
		return atstoreDao.findAll();
	}

	@Override
	public Atstore findById(String id) {
		return atstoreDao.findById(id);
	}

	@Override
	public void save(Atstore atstore) {
		atstoreDao.save(atstore);
	}

	@Override
	public void update(Atstore atstore) {
		atstoreDao.update(atstore);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(List<String> ids) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Atstore a where a.id in (");
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
		atstoreDao.findByHql(ResultType.NO_RESULT, sb.toString(), params);
	}

	@Override
	public Atstore getByGoodsId(String goodsId) {
		String hql = "from Atstore a where a.goodsId = ?";
		Object[] params = new Object[] { goodsId };
		return (Atstore) atstoreDao.findByHql(ResultType.UINQUE_RESULT, hql, params);
	}

	@Override
	public Atstore getByRelationId(String relationId) {
		String hql = "from Atstore a where a.relationId = ?";
		Object[] params = new Object[] { relationId };
		return (Atstore) atstoreDao.findByHql(ResultType.UINQUE_RESULT, hql, params);
	}
	
	@Override
	public Pagination getPagination(Object[] params, Pagination pagination) {
		String hql = "select count(*) from Atstore";
		String hql2 = "from Atstore order by ? ";
		pagination.setResultCount(Integer.parseInt(atstoreDao.countByHql(hql, null).toString()));
		return atstoreDao.getPagination(hql2, params, pagination);
	}
}
