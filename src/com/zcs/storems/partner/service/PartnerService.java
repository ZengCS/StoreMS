package com.zcs.storems.partner.service;

import java.util.List;

import com.zcs.storems.partner.domain.Partner;
import com.zcs.storems.util.Pagination;

public interface PartnerService {
	public Pagination getPagination(Pagination pagination);

	public List<Partner> findAll();

	public Partner findById(String id);

	public void save(Partner partner);

	public void update(Partner partner);

	public void delete(String id);

	public void batchDelete(List<String> ids);

	public List<Partner> listByType(String type);
	
	public Pagination getPagination(Object[] params, Pagination pagination);
}
