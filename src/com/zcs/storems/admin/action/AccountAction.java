package com.zcs.storems.admin.action;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.harmony.framework.util.properties.PropertiesUtil;
import com.harmony.framework.web.struts2.BaseGenericAction;
import com.opensymphony.xwork2.ActionContext;
import com.zcs.storems.admin.domain.Account;
import com.zcs.storems.admin.service.AccountService;
import com.zcs.storems.util.Pagination;
import com.zcs.util.MD5;

public class AccountAction extends BaseGenericAction<Account> {
	private static Properties config = PropertiesUtil.loadFromClassPathResource("/applicationGlobal.properties");
	private static Logger log = Logger.getLogger(AccountAction.class);
	private static final long serialVersionUID = 607187668026853711L;

	private Account account;
	private AccountService accountService;
	private List<String> ids;
	private String paramString;
	private String relationId;
	private Integer currPage = 0;// 当前页码
	private String vcode;

	/**
	 * 用户管理首页action
	 */
	public String accountIndex() {
		return "success";
	}

	public String logout() {
		log.info("注销登录!");
		ActionContext.getContext().getSession().put("account", null);
		ActionContext.getContext().getSession().put("msg", null);
		return "loginFailed";
	}

	public String login() {
		String loginState = "loginFailed";
		log.info("-----登录验证-----\nusername:" + account.getUserName() + ", password:" + account.getPassword());
		String sessionCode = ActionContext.getContext().getSession().get("vCode").toString().toLowerCase();
		log.info("vCode(session): " + sessionCode);
		log.info("vCode(input):" + vcode);
		log.info("accountService:" + accountService);
		// 验证第一步:验证码验证
		if (!sessionCode.equals(vcode.toLowerCase())) {
			log.info("验证码错误!");
			ActionContext.getContext().getSession().put("msg", "验证码错误!");
		}
		// 验证第二步:账号密码验证
		else {
			account = accountService.login(account);
			// 登录已成功
			if (account != null) {
				// 检查账号是否可用
				if (account.getState().equals("0")) {
					log.info("该账号已被锁定,请联系管理员!");
					ActionContext.getContext().getSession().put("msg", "该账号已被锁定,请联系管理员!");
				} else {
					ActionContext.getContext().getSession().put("account", account);
					ActionContext.getContext().getSession().put("msg", "登录成功!");
					loginState = "loginSuccess";
				}
			} else {
				ActionContext.getContext().getSession().put("msg", "账号或者密码错误!");
			}
		}
		return loginState;
	}

	/**
	 * 测试
	 */
	public void accountTest() {
		String appName = config.getProperty("app.name");
		log.info("应用程序名称:" + appName);
		log.info("测试成功!");
		JSONOuter().writeSuccessMsg(getOut(), "test success成功:" + appName);
	}

	/**
	 * 根据ID数组批量删除
	 */
	public void deleteByIds() {
		log.info("根据ids删除记录,ids:" + ids);
		accountService.batchDelete(ids);
		JSONOuter().writeSuccessMsg(getOut(), "成功删除[<font color=red> " + ids.size() + " </font>]条数据!");
	}

	/**
	 * 根据ID获取用户对象
	 */
	public void getById() {
		account = accountService.findById(relationId);
		JSONOuter().writeRecordBean(getOut(), account);
	}

	/**
	 * 获取列表
	 */
	public void list() {
		List<Account> list = accountService.findAll();
		JSONOuter().writeList(getOut(), list);
	}

	/**
	 * 分页查询
	 */
	public void pagelist() {
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currPage);
		pagination.setFirstResult(1);
		// pagination.setList(accountService.findAll());
		pagination.setPageSize(Integer.parseInt(config.getProperty("page.size")));
		pagination.setResultCount(13);

		Object[] params = new Object[] { "userName" };
		pagination = accountService.getPagination(params, pagination);
		JSONOuter().writeRecordBean(getOut(), pagination);
	}

	public void save() {
		// 0账号 1密码 2确认密码 3真实姓名 4性别 5年龄 6身份证号 7联系电话 8地址 9角色级别 10状态
		// paramString:zcs123,123,123,曾成顺,男,23,510125198904113112,13666666666,四川成都,系统管理员,启用,
		log.info("paramString:" + paramString);
		String[] param = paramString.split(",");
		String accountId = "";
		if (param.length == 12) {
			accountId = param[11];
		}
		log.info(accountId);
		if (StringUtils.isEmpty(accountId)) {
			account = new Account();
		} else {
			account = accountService.findById(accountId);
		}
		account.setUserName(param[0]);
		account.setPassword(MD5.getMd5(param[1]));
		account.setRealName(param[3]);
		account.setSex(param[4]);
		account.setAge(Integer.parseInt(param[5]));
		account.setIdCard(param[6]);
		account.setLinkPhone(param[7]);
		account.setAddress(param[8]);
		account.setRoleLevel(param[9]);
		account.setState(param[10]);
		if (StringUtils.isEmpty(accountId)) {
			account.setRegDate(new Date());
			accountService.save(account);
		} else {
			accountService.update(account);
		}
		JSONOuter().writeSuccessMsg(getOut(), "数据保存成功");
	}

	/*--------------------- getter and setter ---------------------*/
	public Account getAccount() {
		return account;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public List<String> getIds() {
		return ids;
	}

	@Override
	public Account getModel() {
		return account;
	}

	public String getParamString() {
		return paramString;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
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

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
}
