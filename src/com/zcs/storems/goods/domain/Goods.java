package com.zcs.storems.goods.domain;

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
@Table(name = "TB_GOODS", catalog = "storedb")
public class Goods implements Serializable {
	private static final long serialVersionUID = 8816011117050954733L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;// 主键

	@Column(name = "NAME", length = 50)
	private String name;// 商品名称

	@Column(name = "CODE", length = 32)
	private String code;// 商品编号

	@Column(name = "TYPE", length = 20)
	private String type;// 商品名称

	@Column(name = "SPECIFICATION", length = 20)
	private String specification;// 规格

	@Column(name = "PRODUCTION_DATE")
	private Date productionDate;// 生产日期

	@Column(name = "SHELF_LIFE")
	private String shelfLife;// 保质期

	@Column(name = "UNIT")
	private String unit;// 计量单位

	@Column(name = "PRICE")
	private String price;// 单价

	@Column(name = "NOTE")
	private String note;// 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
