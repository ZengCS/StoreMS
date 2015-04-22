package com.zcs.storems.admin.domain;

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
@Table(name = "TB_ACCOUNT", catalog = "storedb")
public class Account implements Serializable {
	private static final long serialVersionUID = 32070099294195538L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;// 主键

	@Column(name = "USERNAME", length = 20)
	private String userName;// 用户名

	@Column(name = "PASSWORD", length = 32)
	private String password;// 密码

	@Column(name = "REAL_NAME", length = 20)
	private String realName;// 真实姓名

	@Column(name = "LINK_PHONE", length = 20)
	private String linkPhone;// 联系电话

	@Column(name = "REG_DATE", length = 32)
	private Date regDate;// 注册日期

	@Column(name = "SEX", length = 2)
	private String sex;// 性别

	@Column(name = "AGE")
	private int age;// 年龄

	@Column(name = "ADDRESS", length = 200)
	private String address;// 地址

	@Column(name = "ID_CARD", length = 20)
	private String idCard;// 身份证号码

	@Column(name = "ROLE_LEVEL", length = 20)
	private String roleLevel;// 角色级别

	@Column(name = "STATE", length = 20)
	private String state;// 状态

	@Column(name = "NOTE", length = 200)
	private String note;// 备注信息

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
