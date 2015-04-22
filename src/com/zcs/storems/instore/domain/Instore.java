package com.zcs.storems.instore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author ZengCS 用户信息管理类 2013年6月9日12:49:45
 */
@Entity
@Table(name = "TB_INSTORE", catalog = "storedb")
public class Instore implements Serializable {
	private static final long serialVersionUID = 32070099294195538L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;// 主键

	@Column(name = "GOODS_ID", length = 32)
	private String goodsId;// 商品ID

	@Column(name = "PROVIDER_ID", length = 32)
	private String providerId;// 供货商ID

	@Column(name = "USER_ID", length = 32)
	private String userId;// 用户ID

	@Column(name = "IN_DATE")
	private Date inDate;// 入库时间

	@Column(name = "PRICE", length = 20)
	private String price;// 入库价格

	@Column(name = "AMOUNT")
	private Integer amount;// 入库数量

	@Column(name = "NOTE", length = 200)
	private String note;// 备注

	@Column(name = "GOODS_NAME", length = 50)
	private String goodsName;// 商品对象

	@Column(name = "PROVIDER_NAME", length = 50)
	private String providerName;// 合作伙伴

	@Column(name = "USERNAME", length = 50)
	private String userName;// 操作人员

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
