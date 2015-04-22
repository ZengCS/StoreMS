/*package com.zcs.storems.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class AccountInterceptor implements Interceptor {

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation interceptor) throws Exception {
		String method = interceptor.getProxy().getMethod();
		System.out.println("method:" + method);
		if (method.equals("login") || ActionContext.getContext().getSession().get("account") != null) {
			return interceptor.invoke();
		} else {
			ActionContext.getContext().getSession().put("msg", "nologin");
			return "input";
		}
	}
}
*/