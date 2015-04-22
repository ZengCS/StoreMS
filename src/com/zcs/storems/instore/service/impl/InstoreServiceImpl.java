package com.zcs.storems.instore.service.impl;

import java.util.List;

import com.zcs.storems.instore.dao.InstoreDao;
import com.zcs.storems.instore.domain.Instore;
import com.zcs.storems.instore.service.InstoreService;
import com.zcs.storems.util.Pagination;
import com.zcs.storems.util.ResultType;

public class InstoreServiceImpl implements InstoreService {
	private InstoreDao instoreDao;

	public void setInstoreDao(InstoreDao instoreDao) {
		this.instoreDao = instoreDao;
	}

	@Override
	public Pagination getPagination(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instore> findAll() {
		return instoreDao.findAll();
	}

	@Override
	public Instore findById(String id) {
		return instoreDao.findById(id);
	}

	@Override
	public void save(Instore instore) {
		instoreDao.save(instore);
	}

	@Override
	public void update(Instore instore) {
		instoreDao.update(instore);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(List<String> ids) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Instore a where a.id in (");
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
		instoreDao.findByHql(ResultType.NO_RESULT, sb.toString(), params);
	}

	@Override
	public Pagination getPagination(Object[] params, Pagination pagination) {
		String hql = "select count(*) from Instore";
		String hql2 = "from Instore order by ? desc";
		pagination.setResultCount(Integer.parseInt(instoreDao.countByHql(hql, null).toString()));
		return instoreDao.getPagination(hql2, params, pagination);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Instore> statisticalByParam(Object[] param, String type) {
		String hql = "from Instore where inDate >=? and inDate<= ? order by inDate desc";
		return (List<Instore>) instoreDao.findByHql(ResultType.MULTI_RESULT, hql, param);
	}
}
