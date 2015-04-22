package com.zcs.storems.partner.domain;

import java.io.Serializable;

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
@Table(name = "TB_PARTNER", catalog = "storedb")
public class Partner implements Serializable {
	private static final long serialVersionUID = 32070099294195538L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;// 主键

	@Column(name = "NAME", length = 50)
	private String name;// 名称

	@Column(name = "TYPE", length = 50)
	private String type;// 名称

	@Column(name = "PROPERTY", length = 50)
	private String property;// 名称

	@Column(name = "ADDRESS", length = 50)
	private String address;// 名称

	@Column(name = "LINK_MAN", length = 50)
	private String linkMan;// 名称

	@Column(name = "LINK_PHONE", length = 50)
	private String linkPhone;// 名称

	@Column(name = "TELPHONE", length = 50)
	private String telphone;// 名称

	@Column(name = "NOTE", length = 50)
	private String note;// 名称

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
