package com.zcs.storems.outstore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author ZengCS 商品出库信息 2013年6月18日20:45:57
 */
@Entity
@Table(name = "TB_OUTSTORE", catalog = "storedb")
public class Outstore implements Serializable {
	private static final long serialVersionUID = 32070099294195538L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;// 主键

	@Column(name = "GOODS_ID", length = 32)
	private String goodsId;// 商品ID

	@Column(name = "CONSUMER_ID", length = 32)
	private String consumerId;// 采购商ID

	@Column(name = "USER_ID", length = 32)
	private String userId;// 用户ID

	@Column(name = "OUT_DATE")
	private Date outDate;// 出库时间

	@Column(name = "PRICE", length = 20)
	private String price;// 出库价格

	@Column(name = "AMOUNT")
	private Integer amount;// 出库数量

	@Column(name = "NOTE", length = 200)
	private String note;// 备注

	@Column(name = "GOODS_NAME", length = 50)
	private String goodsName;// 商品对象

	@Column(name = "CONSUMER_NAME", length = 50)
	private String consumerName;// 合作伙伴

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

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
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

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
