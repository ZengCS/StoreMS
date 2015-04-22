package com.zcs.storems.atstore.action;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.harmony.framework.util.properties.PropertiesUtil;
import com.harmony.framework.web.struts2.BaseGenericAction;
import com.zcs.storems.atstore.domain.Atstore;
import com.zcs.storems.atstore.service.AtstoreService;
import com.zcs.storems.goods.domain.Goods;
import com.zcs.storems.goods.service.GoodsService;
import com.zcs.storems.util.Pagination;

public class AtstoreAction extends BaseGenericAction<Atstore> {
	private static Properties config = PropertiesUtil.loadFromClassPathResource("/applicationGlobal.properties");
	private static Logger log = Logger.getLogger(AtstoreAction.class);
	private static final long serialVersionUID = 607187668026853711L;

	private Atstore atstore;
	private AtstoreService atstoreService;
	private GoodsService goodsService;// 商品服务
	private List<String> ids;
	private String paramString;
	private String relationId;

	/**
	 * 用户管理首页action
	 */
	public String atstoreIndex() {
		return "success";
	}

	/**
	 * 测试
	 */
	public void atstoreTest() {
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
		atstoreService.batchDelete(ids);
		JSONOuter().writeSuccessMsg(getOut(), "成功删除[ " + ids.size() + " ]条数据!");
	}

	/**
	 * 根据ID获取用户对象
	 */
	public void getById() {
		atstore = atstoreService.findById(relationId);
		JSONOuter().writeRecordBean(getOut(), atstore);
	}

	/**
	 * 获取列表
	 */
	public void list() {
		System.out.println(goodsService);
		List<Atstore> list = atstoreService.findAll();
		Goods goods = null;
		for (Atstore obj : list) {
			goods = goodsService.findById(obj.getGoodsId());
			obj.setGoodsName(goods.getName());
			obj.setUnit(goods.getUnit());
		}
		JSONOuter().writeList(getOut(), list);
	}

	/**
	 * 分页查询
	 */
	public void pagelist() {
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currPage);
		pagination.setFirstResult(1);
		pagination.setList(atstoreService.findAll());
		pagination.setPageSize(Integer.parseInt(config.getProperty("page.size")));
		pagination.setResultCount(13);

		Object[] params = new Object[] { "amount" };
		pagination = atstoreService.getPagination(params, pagination);
		JSONOuter().writeRecordBean(getOut(), pagination);
	}

	public void save() {
		// 0账号 1密码 2确认密码 3真实姓名 4性别 5年龄 6身份证号 7联系电话 8地址 9角色级别 10状态
		// paramString:zcs123,123,123,曾成顺,男,23,510125198904113112,13666666666,四川成都,系统管理员,启用,
		System.out.println("paramString:" + paramString);
		String[] param = paramString.split(",");
		String atstoreId = "";
		if (param.length == 12) {
			atstoreId = param[11];
		}
		System.out.println(atstoreId);
		if (StringUtils.isEmpty(atstoreId)) {
			atstore = new Atstore();
		} else {
			atstore = atstoreService.findById(param[11]);
		}
		if (StringUtils.isEmpty(atstoreId)) {
			atstoreService.save(atstore);
		} else {
			atstoreService.update(atstore);
		}
		JSONOuter().writeSuccessMsg(getOut(), "数据保存成功");
	}

	/*--------------------- getter and setter ---------------------*/
	public Atstore getAtstore() {
		return atstore;
	}

	public AtstoreService getAtstoreService() {
		return atstoreService;
	}

	public List<String> getIds() {
		return ids;
	}

	@Override
	public Atstore getModel() {
		return atstore;
	}

	public String getParamString() {
		return paramString;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setAtstore(Atstore atstore) {
		this.atstore = atstore;
	}

	public void setAtstoreService(AtstoreService atstoreService) {
		this.atstoreService = atstoreService;
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

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	private Integer currPage = 0;// 当前页码

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
}
