package com.zcs.storems.instore.action;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.harmony.framework.util.properties.PropertiesUtil;
import com.harmony.framework.web.struts2.BaseGenericAction;
import com.zcs.storems.admin.service.AccountService;
import com.zcs.storems.atstore.domain.Atstore;
import com.zcs.storems.atstore.service.AtstoreService;
import com.zcs.storems.goods.domain.Goods;
import com.zcs.storems.goods.service.GoodsService;
import com.zcs.storems.instore.domain.Instore;
import com.zcs.storems.instore.service.InstoreService;
import com.zcs.storems.partner.domain.Partner;
import com.zcs.storems.partner.service.PartnerService;
import com.zcs.storems.util.Pagination;

public class InstoreAction extends BaseGenericAction<Instore> {
	private static Properties config = PropertiesUtil.loadFromClassPathResource("/applicationGlobal.properties");
	private static Logger log = Logger.getLogger(InstoreAction.class);
	private static final long serialVersionUID = 607187668026853711L;

	private Instore instore;
	private InstoreService instoreService;
	private GoodsService goodsService;
	private PartnerService partnerService;
	private AccountService accountService;
	private AtstoreService atstoreService;

	private List<String> ids;
	private String paramString;
	private String relationId;

	/**
	 * 用户管理首页action
	 */
	public String instoreIndex() {
		return "success";
	}

	/**
	 * 获取商品下拉列表
	 */
	public void getGoodsSelect() {
		StringBuffer sb = new StringBuffer();
		List<Goods> list = goodsService.findAll();
		sb.append("<option value='none'>-- 请选择 --</option>");
		for (Goods g : list) {
			sb.append("<option value='" + g.getId() + "'>" + g.getName() + "[" + g.getUnit() + "]</option>");
		}
		JSONOuter().writeSuccessMsg(getOut(), sb.toString());
	}

	/**
	 * 获取供应商下拉列表
	 */
	public void getProviderSelect() {
		StringBuffer sb = new StringBuffer();
		List<Partner> list = partnerService.listByType("provider");
		sb.append("<option value='none'>-- 请选择 --</option>");
		for (Partner p : list) {
			sb.append("<option value='" + p.getId() + "'>" + p.getName() + "</option>");
		}
		JSONOuter().writeSuccessMsg(getOut(), sb.toString());
	}

	/**
	 * 测试
	 */
	public void instoreTest() {
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
		instoreService.batchDelete(ids);
		JSONOuter().writeSuccessMsg(getOut(), "成功删除[ " + ids.size() + " ]条数据!");
	}

	/**
	 * 根据ID获取用户对象
	 */
	public void getById() {
		instore = instoreService.findById(relationId);
		JSONOuter().writeRecordBean(getOut(), instore);
	}

	/**
	 * 获取列表
	 */
	public void list() {
		List<Instore> list = instoreService.findAll();
		JSONOuter().writeList(getOut(), list);
	}

	/**
	 * 分页查询
	 */
	public void pagelist() {
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currPage);
		pagination.setFirstResult(1);
		pagination.setList(instoreService.findAll());
		pagination.setPageSize(Integer.parseInt(config.getProperty("page.size")));
		pagination.setResultCount(13);

		Object[] params = new Object[] { "inDate" };
		pagination = instoreService.getPagination(params, pagination);
		JSONOuter().writeRecordBean(getOut(), pagination);
	}

	public void save() {
		// 0商品ID 1数量 2单价 3供应商id 4备注 5id
		// paramString:40289a813f69f88b013f6a023cfe0004,200,1,provider-2,备注信息啊,id
		System.out.println("paramString:" + paramString);
		String[] param = paramString.split(",");
		String instoreId = "";
		if (param.length == 8) {
			instoreId = param[7];
		}
		System.out.println(instoreId);
		if (StringUtils.isEmpty(instoreId)) {
			instore = new Instore();
		} else {
			instore = instoreService.findById(instoreId);
		}
		// 为对象设置属性
		instore.setGoodsId(param[0]);
		instore.setAmount(Integer.parseInt(param[1]));
		instore.setPrice(param[2]);
		instore.setProviderId(param[3]);
		instore.setNote(param[4]);
		instore.setGoodsName(param[5]);
		instore.setProviderName(param[6]);

		instore.setUserId("currentLoginUserId");
		instore.setUserName("管理员");

		if (StringUtils.isEmpty(instoreId)) {
			instore.setInDate(new Date());
			instoreService.save(instore);
		} else {
			instoreService.update(instore);
		}
		this.updateAtstoreInfo(instore);
		JSONOuter().writeSuccessMsg(getOut(), "数据保存成功");
	}

	/**
	 * 更新在库信息 1.根据关联ID获取在库信息 2.根据商品ID获取在库信息
	 */
	private void updateAtstoreInfo(Instore obj) {
		Atstore atstore = atstoreService.getByRelationId(obj.getId());
		if (atstore != null) {
			atstore.setAmount(obj.getAmount());
			atstore.setGoodsId(obj.getGoodsId());
			atstore.setRelationId(obj.getId());
			atstoreService.update(atstore);
		} else {
			atstore = atstoreService.getByGoodsId(obj.getGoodsId());
			if (atstore == null) {
				Goods goods = goodsService.findById(obj.getGoodsId());
				atstore = new Atstore();
				atstore.setAmount(obj.getAmount());
				atstore.setGoodsId(obj.getGoodsId());
				atstore.setRelationId(obj.getId());
				atstore.setUnit(goods.getUnit());
				atstore.setGoodsName(goods.getName());
				atstoreService.save(atstore);
			} else {
				atstore.setAmount(atstore.getAmount() + obj.getAmount());
				atstore.setRelationId(obj.getId());
				atstoreService.update(atstore);
			}
		}
	}

	/*--------------------- getter and setter ---------------------*/
	public Instore getInstore() {
		return instore;
	}

	public InstoreService getInstoreService() {
		return instoreService;
	}

	public List<String> getIds() {
		return ids;
	}

	@Override
	public Instore getModel() {
		return instore;
	}

	public String getParamString() {
		return paramString;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setInstore(Instore instore) {
		this.instore = instore;
	}

	public void setInstoreService(InstoreService instoreService) {
		this.instoreService = instoreService;
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

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public PartnerService getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public AtstoreService getAtstoreService() {
		return atstoreService;
	}

	public void setAtstoreService(AtstoreService atstoreService) {
		this.atstoreService = atstoreService;
	}

	private Integer currPage = 0;// 当前页码

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
}
