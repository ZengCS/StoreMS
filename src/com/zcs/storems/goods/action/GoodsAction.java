package com.zcs.storems.goods.action;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.harmony.framework.util.date.DateFormatUtils;
import com.harmony.framework.util.properties.PropertiesUtil;
import com.harmony.framework.web.struts2.BaseGenericAction;
import com.zcs.storems.goods.domain.Goods;
import com.zcs.storems.goods.service.GoodsService;
import com.zcs.storems.util.Pagination;

public class GoodsAction extends BaseGenericAction<Goods> {
	private static Properties config = PropertiesUtil.loadFromClassPathResource("/applicationGlobal.properties");
	private static Logger log = Logger.getLogger(GoodsAction.class);
	private static final long serialVersionUID = 607187668026853711L;

	private Goods goods;
	private GoodsService goodsService;
	private List<String> ids;
	private String paramString;
	private String relationId;

	/**
	 * 用户管理首页action
	 */
	public String goodsIndex() {
		return "success";
	}

	/**
	 * 测试
	 */
	public void goodsTest() {
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
		goodsService.batchDelete(ids);
		JSONOuter().writeSuccessMsg(getOut(), "成功删除[ " + ids.size() + " ]条数据!");
	}

	/**
	 * 根据ID获取用户对象
	 */
	public void getById() {
		goods = goodsService.findById(relationId);
		JSONOuter("yyyy-MM-dd").writeRecordBean(getOut(), goods);
	}

	/**
	 * 获取列表
	 */
	public void list() {
		List<Goods> list = goodsService.findAll();
		JSONOuter("yyyy-MM-dd").writeList(getOut(), list);
	}
	
	/**
	 * 分页查询
	 */
	public void pagelist() {
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currPage);
		pagination.setFirstResult(1);
		pagination.setList(goodsService.findAll());
		pagination.setPageSize(Integer.parseInt(config.getProperty("page.size")));
		pagination.setResultCount(13);

		Object[] params = new Object[] { "productionDate" };
		pagination = goodsService.getPagination(params, pagination);
		JSONOuter("yyyy-MM-dd").writeRecordBean(getOut(), pagination);
	}

	public void save() {
		// 0名称 1编码 2类型 3规格 4单位 5单价 6生产日期 7保质期 8备注 9ID
		// paramString:油条,yt,食品,20克,条,1.00,2013-11-11,12,好吃的油条哦,id
		System.out.println("paramString:" + paramString);
		String[] param = paramString.split(",");
		String goodsId = "";
		if (param.length == 10) {
			goodsId = param[9];
		}
		System.out.println(goodsId);
		if (StringUtils.isEmpty(goodsId)) {
			goods = new Goods();
		} else {
			goods = goodsService.findById(param[9]);
		}
		// 为对象填充属性
		goods.setName(param[0]);
		goods.setCode(param[1]);
		goods.setType(param[2]);
		goods.setSpecification(param[3]);
		goods.setUnit(param[4]);
		goods.setPrice(param[5]);
		goods.setProductionDate(DateFormatUtils.strToDate(param[6]));
		goods.setShelfLife(param[7]);
		goods.setNote(param[8]);

		if (StringUtils.isEmpty(goodsId)) {
			goodsService.save(goods);
		} else {
			goodsService.update(goods);
		}
		JSONOuter().writeSuccessMsg(getOut(), "数据保存成功");
	}

	/*--------------------- getter and setter ---------------------*/
	public Goods getGoods() {
		return goods;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public List<String> getIds() {
		return ids;
	}

	@Override
	public Goods getModel() {
		return goods;
	}

	public String getParamString() {
		return paramString;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
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
