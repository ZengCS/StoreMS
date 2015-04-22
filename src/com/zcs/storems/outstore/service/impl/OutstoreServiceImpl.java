package com.zcs.storems.outstore.service.impl;

import java.util.List;

import com.zcs.storems.outstore.dao.OutstoreDao;
import com.zcs.storems.outstore.domain.Outstore;
import com.zcs.storems.outstore.service.OutstoreService;
import com.zcs.storems.util.Pagination;
import com.zcs.storems.util.ResultType;

public class OutstoreServiceImpl implements OutstoreService {
	private OutstoreDao outstoreDao;

	public void setOutstoreDao(OutstoreDao outstoreDao) {
		this.outstoreDao = outstoreDao;
	}

	@Override
	public Pagination getPagination(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Outstore> findAll() {
		return outstoreDao.findAll();
	}

	@Override
	public Outstore findById(String id) {
		return outstoreDao.findById(id);
	}

	@Override
	public void save(Outstore outstore) {
		outstoreDao.save(outstore);
	}

	@Override
	public void update(Outstore outstore) {
		outstoreDao.update(outstore);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(List<String> ids) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Outstore a where a.id in (");
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
		outstoreDao.findByHql(ResultType.NO_RESULT, sb.toString(), params);
	}

	@Override
	public Pagination getPagination(Object[] params, Pagination pagination) {
		String hql = "select count(*) from Outstore";
		String hql2 = "from Outstore order by ? desc";
		pagination.setResultCount(Integer.parseInt(outstoreDao.countByHql(hql, null).toString()));
		return outstoreDao.getPagination(hql2, params, pagination);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Outstore> statisticalByParam(Object[] param, String type) {
		String hql = "from Outstore where outDate >=? and outDate<= ? order by outDate desc";
		return (List<Outstore>) outstoreDao.findByHql(ResultType.MULTI_RESULT, hql, param);
	}
}
