package com.zcs.storems.atstore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author ZengCS 在库信息管理 2013年6月18日11:35:43
 */
@Entity
@Table(name = "TB_ATSTORE", catalog = "storedb")
public class Atstore implements Serializable {
	private static final long serialVersionUID = -6277004683668540400L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;// 主键

	@Column(name = "RELATION_ID", length = 32)
	private String relationId;// 关联入库/出库ID

	@Column(name = "GOODS_ID", length = 32)
	private String goodsId;// 商品ID

	@Column(name = "AMOUNT")
	private Integer amount;// 数量

	@Column(name = "NOTE", length = 200)
	private String note;// 备注

	@Column(name = "GOODS_NAME", length = 50)
	private String goodsName;// 商品名称

	@Column(name = "UINT", length = 10)
	private String unit;// 单位

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

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
