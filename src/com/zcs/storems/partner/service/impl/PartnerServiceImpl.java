package com.zcs.storems.partner.service.impl;

import java.util.List;

import com.zcs.storems.partner.dao.PartnerDao;
import com.zcs.storems.partner.domain.Partner;
import com.zcs.storems.partner.service.PartnerService;
import com.zcs.storems.util.Pagination;
import com.zcs.storems.util.ResultType;

public class PartnerServiceImpl implements PartnerService {
	private PartnerDao partnerDao;

	public void setPartnerDao(PartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

	@Override
	public Pagination getPagination(Pagination pagination) {
		return null;
	}

	@Override
	public List<Partner> findAll() {
		return partnerDao.findAll();
	}

	@Override
	public Partner findById(String id) {
		return partnerDao.findById(id);
	}

	@Override
	public void save(Partner partner) {
		partnerDao.save(partner);
	}

	@Override
	public void update(Partner partner) {
		partnerDao.update(partner);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(List<String> ids) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Partner a where a.id in (");
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
		partnerDao.findByHql(ResultType.NO_RESULT, sb.toString(), params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partner> listByType(String type) {
		String hql = "from Partner p where p.type=?";
		Object[] params = new Object[] { type };
		return (List<Partner>) partnerDao.findByHql(ResultType.MULTI_RESULT, hql, params);
	}

	@Override
	public Pagination getPagination(Object[] params, Pagination pagination) {
		String hql = "select count(*) from Partner where type='" + params[0] + "'";
		String hql2 = "from Partner p where p.type=? order by ? ";
		pagination.setResultCount(Integer.parseInt(partnerDao.countByHql(hql, null).toString()));
		return partnerDao.getPagination(hql2, params, pagination);
	}
}
