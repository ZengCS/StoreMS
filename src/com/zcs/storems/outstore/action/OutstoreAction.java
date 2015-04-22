package com.zcs.storems.outstore.action;

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
import com.zcs.storems.outstore.domain.Outstore;
import com.zcs.storems.outstore.service.OutstoreService;
import com.zcs.storems.partner.domain.Partner;
import com.zcs.storems.partner.service.PartnerService;
import com.zcs.storems.util.Pagination;

public class OutstoreAction extends BaseGenericAction<Outstore> {
	private static Properties config = PropertiesUtil.loadFromClassPathResource("/applicationGlobal.properties");
	private static Logger log = Logger.getLogger(OutstoreAction.class);
	private static final long serialVersionUID = 607187668026853711L;

	private Outstore outstore;
	private OutstoreService outstoreService;
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
	public String outstoreIndex() {
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
	 * 获取消费者下拉列表
	 */
	public void getConsumerSelect() {
		StringBuffer sb = new StringBuffer();
		List<Partner> list = partnerService.listByType("consumer");
		sb.append("<option value='none'>-- 请选择 --</option>");
		for (Partner p : list) {
			sb.append("<option value='" + p.getId() + "'>" + p.getName() + "</option>");
		}
		JSONOuter().writeSuccessMsg(getOut(), sb.toString());
	}

	/**
	 * 测试
	 */
	public void outstoreTest() {
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
		outstoreService.batchDelete(ids);
		JSONOuter().writeSuccessMsg(getOut(), "成功删除[ " + ids.size() + " ]条数据!");
	}

	/**
	 * 根据ID获取用户对象
	 */
	public void getById() {
		outstore = outstoreService.findById(relationId);
		JSONOuter().writeRecordBean(getOut(), outstore);
	}

	/**
	 * 获取列表
	 */
	public void list() {
		List<Outstore> list = outstoreService.findAll();
		JSONOuter().writeList(getOut(), list);
	}
	
	/**
	 * 分页查询
	 */
	public void pagelist() {
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currPage);
		pagination.setFirstResult(1);
		pagination.setList(outstoreService.findAll());
		pagination.setPageSize(Integer.parseInt(config.getProperty("page.size")));
		pagination.setResultCount(13);

		Object[] params = new Object[] { "outDate" };
		pagination = outstoreService.getPagination(params, pagination);
		JSONOuter().writeRecordBean(getOut(), pagination);
	}

	public void save() {
		// 0商品ID 1数量 2单价 3供应商id 4备注 5商品名称 6消费者名称 7id
		// paramString:40289a813f69f88b013f6a023cfe0004,200,1,provider-2,备注信息啊,商品名称,消费者名称,id
		System.out.println("paramString:" + paramString);
		String[] param = paramString.split(",");
		String outstoreId = "";
		if (param.length == 8) {
			outstoreId = param[7];
		}
		System.out.println(outstoreId);
		if (StringUtils.isEmpty(outstoreId)) {
			outstore = new Outstore();
		} else {
			outstore = outstoreService.findById(outstoreId);
		}
		// 为对象设置属性
		outstore.setGoodsId(param[0]);
		outstore.setAmount(Integer.parseInt(param[1]));
		outstore.setPrice(param[2]);
		outstore.setConsumerId(param[3]);
		outstore.setNote(param[4]);
		outstore.setGoodsName(param[5]);
		outstore.setConsumerName(param[6]);

		outstore.setUserId("currentLoginUserId");
		outstore.setUserName("管理员");

		if (StringUtils.isEmpty(outstoreId)) {
			outstore.setOutDate(new Date());
			outstoreService.save(outstore);
		} else {
			outstoreService.update(outstore);
		}
		this.updateAtstoreInfo(outstore);
		JSONOuter().writeSuccessMsg(getOut(), "数据保存成功");
	}

	/**
	 * 更新在库信息 1.根据关联ID获取在库信息 2.根据商品ID获取在库信息
	 */
	private void updateAtstoreInfo(Outstore obj) {
		Atstore atstore = atstoreService.getByGoodsId(obj.getGoodsId());
		if (atstore != null) {
			atstore.setAmount(atstore.getAmount() - obj.getAmount());
			atstore.setRelationId(obj.getId());
			atstoreService.update(atstore);
		}
	}

	/*--------------------- getter and setter ---------------------*/
	public Outstore getOutstore() {
		return outstore;
	}

	public OutstoreService getOutstoreService() {
		return outstoreService;
	}

	public List<String> getIds() {
		return ids;
	}

	@Override
	public Outstore getModel() {
		return outstore;
	}

	public String getParamString() {
		return paramString;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setOutstore(Outstore outstore) {
		this.outstore = outstore;
	}

	public void setOutstoreService(OutstoreService outstoreService) {
		this.outstoreService = outstoreService;
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
	}private Integer currPage = 0;// 当前页码
	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
}
