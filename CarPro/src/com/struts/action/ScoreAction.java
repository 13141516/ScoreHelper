package com.struts.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.db.business.CustomerBusiness;
import com.struts.model.Customer;
import com.struts.utils.TestAccount;

import net.sf.json.JSONObject;

public class ScoreAction extends ActionSupport implements ServletResponseAware {

	private static final long serialVersionUID = 5882740199673950184L;
	
	private HttpServletResponse response;
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	public String insert() throws Exception {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		TestAccount ta = new TestAccount();
		String flag = ta.selectFromPython(customer.getUserName(), customer.getUserPassword()).toString();
		JSONObject jo = JSONObject.fromObject(flag);
		if((int)(jo.getInt("state")) == 1) {
			if(CustomerBusiness.insertItem(customer) == 1) {
				addActionError("插入成功");
			} else {
				addActionError("插入失败");
			}
		} else {
			addActionError("用户名或密码不正确");
		}
		return INPUT;
	}
	
	public void validateInsert() throws Exception {
		try {
			Long.parseLong(customer.getUserEmail());
		} catch(NumberFormatException e) {
			addFieldError("email", "email must be a number!");
		}
		if(customer.getUserName().length() == 0 || customer.getUserPassword().length() == 0) {
			addFieldError("notification", "username or password must be a number!");
		}
	}
	
}
