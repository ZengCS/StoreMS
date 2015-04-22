package com.zcs.storems.partner.action;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.harmony.framework.util.properties.PropertiesUtil;
import com.harmony.framework.web.struts2.BaseGenericAction;
import com.zcs.storems.partner.domain.Partner;
import com.zcs.storems.partner.service.PartnerService;
import com.zcs.storems.util.Pagination;

public class PartnerAction extends BaseGenericAction<Partner> {
	private static Properties config = PropertiesUtil.loadFromClassPathResource("/applicationGlobal.properties");
	private static Logger log = Logger.getLogger(PartnerAction.class);
	private static final long serialVersionUID = 607187668026853711L;

	private Partner partner;
	private PartnerService partnerService;
	private List<String> ids;
	private String paramString;
	private String relationId;

	/**
	 * 用户管理首页action
	 */
	public String providerIndex() {
		return "provider";
	}

	public String consumerIndex() {
		return "consumer";
	}

	/**
	 * 测试
	 */
	public void partnerTest() {
		String appName = config.getProperty("app.name");
		System.out.println("应用程序名称:" + appName);
		log.info("测试成功!");
		JSONOuter().writeSuccessMsg(getOut(), "test success成功:" + appName);
	}

	/**
	 * 根据ID数组批量删除
	 */
	public void deleteByIds() {
		System.out.println("根据ids删除记录,ids:" + ids);
		partnerService.batchDelete(ids);
		JSONOuter().writeSuccessMsg(getOut(), "成功删除[ " + ids.size() + " ]条数据!");
	}

	/**
	 * 根据ID获取用户对象
	 */
	public void getById() {
		partner = partnerService.findById(relationId);
		JSONOuter().writeRecordBean(getOut(), partner);
	}

	/**
	 * 获取列表
	 */
	public void list() {
		List<Partner> list = partnerService.findAll();
		JSONOuter().writeList(getOut(), list);
	}

	/**
	 * 分页查询
	 */
	private Pagination pagelistByType(String type) {
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currPage);
		pagination.setFirstResult(1);
		pagination.setPageSize(Integer.parseInt(config.getProperty("page.size")));

		Object[] params = new Object[] { type, "name" };
		pagination = partnerService.getPagination(params, pagination);
		return pagination;
	}

	/**
	 * 获取供货商列表
	 */
	public void listProvider() {
		Pagination pagination = this.pagelistByType("provider");
		JSONOuter().writeRecordBean(getOut(), pagination);
	}

	/**
	 * 获取客户 列表
	 */
	public void listConsumer() {
		Pagination pagination = this.pagelistByType("consumer");
		JSONOuter().writeRecordBean(getOut(), pagination);
	}

	public void save() {
		// 0:type 1:name 2:property 3:联系人 4:联系电话 5:办公电话 6:地址 7:备注 8:id
		// paramString:provider,红旗连锁,超市,曾成顺,13880074478,028-88888888,成都市总府路,好大的超市啊啊啊啊啊啊,
		System.out.println("paramString:" + paramString);
		String[] param = paramString.split(",");
		String partnerId = "";
		if (param.length == 9) {
			partnerId = param[8];
		}
		System.out.println(partnerId);
		if (StringUtils.isEmpty(partnerId)) {
			partner = new Partner();
		} else {
			partner = partnerService.findById(partnerId);
		}
		// 为对象添加属性
		partner.setType(param[0]);
		partner.setName(param[1]);
		partner.setProperty(param[2]);
		partner.setLinkMan(param[3]);
		partner.setLinkPhone(param[4]);
		partner.setTelphone(param[5]);
		partner.setAddress(param[6]);
		partner.setNote(param[7]);

		if (StringUtils.isEmpty(partnerId)) {
			partnerService.save(partner);
		} else {
			partnerService.update(partner);
		}
		JSONOuter().writeSuccessMsg(getOut(), "数据保存成功");
	}

	/*--------------------- getter and setter ---------------------*/
	public Partner getPartner() {
		return partner;
	}

	public PartnerService getPartnerService() {
		return partnerService;
	}

	public List<String> getIds() {
		return ids;
	}

	@Override
	public Partner getModel() {
		return partner;
	}

	public String getParamString() {
		return paramString;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public void setParamString(String paramString) {
		this.paramString = paramString;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	private Integer currPage = 0;// 当前页码

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
}
