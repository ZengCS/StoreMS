package com.zcs.storems.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.zcs.storems.admin.domain.Account;
import com.zcs.storems.goods.domain.Goods;
import com.zcs.storems.instore.domain.Instore;
import com.zcs.storems.util.HibernateUtil;
import com.zcs.util.MD5;

/**
 * @author ZengCS 初始化数据库 2013年6月9日12:52:46
 */
public class InitDB {
	private static Logger log = Logger.getLogger(InitDB.class);

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		int MaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		String monthHead = new SimpleDateFormat("yyyy-MM").format(new Date());
		String monthStartStr = monthHead + "-1 00:00:00";
		String monthEndStr = monthHead + "-" + MaxDay + " 23:59:59";
		Date monteStart = null;
		Date monteEnd = null;
		try {
			monteStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(monthStartStr);
			monteEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(monthEndStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(monteStart);
		System.out.println(monteEnd);

		initAccount();
		initGoods();
		initInstore();
	}

	@SuppressWarnings("unchecked")
	public static void list() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		List<Account> list = new ArrayList<Account>();
		list = session.createCriteria(Account.class).list();
		System.out.println("---------------------------------");
		for (Account a : list) {
			System.out.print("id: " + a.getId());
			System.out.print(" | username: " + a.getUserName());
			System.out.print(" | password: " + a.getPassword());
			System.out.print(" | realname: " + a.getRealName());
			System.out.print(" | address: " + a.getAddress());
			System.out.println(" | linkphone: " + a.getLinkPhone());
		}
		System.out.println("---------------------------------");
		session.getTransaction().commit();
	}

	private static void initAccount() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		log.info("初始化数据库[Account]...");
		Account account = null;
		account = new Account();
		account.setUserName("admin");
		account.setPassword(MD5.getMd5("admin"));
		account.setRealName("管理员");
		account.setAddress("四川省成都市");
		account.setAge(18);
		account.setIdCard("510111111111111111");
		account.setLinkPhone("13888888888");
		account.setRegDate(new Date());
		account.setSex("男");
		account.setState("1");
		account.setRoleLevel("系统管理员");
		session.save(account);
		session.getTransaction().commit();
	}

	private static void initGoods() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		log.info("初始化数据库[Goods]...");
		Goods goods = null;
		for (int i = 0; i < 25; i++) {
			goods = new Goods();
			goods.setCode("dmy-" + i);
			goods.setName("台湾大麻圆-" + i);
			goods.setNote("备注信息");
			goods.setPrice(i + "");
			goods.setProductionDate(new Date());
			goods.setShelfLife(i + "");
			goods.setSpecification(i + "克");
			goods.setType("食物");
			goods.setUnit("个");
			session.save(goods);
		}
		session.getTransaction().commit();
	}

	private static void initInstore() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		log.info("初始化数据库[Goods]...");
		Instore instore = null;
		for (int i = 0; i < 25; i++) {
			instore = new Instore();
			instore.setAmount(i * 100);
			instore.setGoodsName("大麻圆-" + i);
			session.save(instore);
		}
		session.getTransaction().commit();
	}
}
