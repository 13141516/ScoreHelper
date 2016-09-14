package com.struts.action;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.struts.db.business.CustomerBusiness;
import com.struts.model.CusOther;
import com.struts.utils.PythonCall;

import net.sf.json.JSONObject;

public class Daemon implements ServletContextListener {
	private MyThread myThread = null;

	public void contextDestroyed(ServletContextEvent e) {
		if (myThread != null && myThread.isInterrupted()) {
			myThread.interrupt();
		}
	}

	public void contextInitialized(ServletContextEvent e) {
		if (myThread == null) {
			myThread = new MyThread();
			myThread.start(); // servlet 上下文初始化时启动 socket
		}
	}
}

class MyThread extends Thread {
	private static PythonCall pc;
	List<String> _list;
	
	public MyThread() {
		// TODO Auto-generated constructor stub
		 pc = new PythonCall();
		 _list = CustomerBusiness.getNameList();
	}
	
	public void run() {
		while (!this.isInterrupted()) {// 线程未中断执行循环
			for(String str:_list) {
				CusOther customer = CustomerBusiness.getCustomer(str);
				try {
					String content = pc.selectFromPython(customer.getUser_name(), customer.getUser_password(), customer.getUser_mail(), customer.getUser_numbers()+"").toString();
					JSONObject jo = JSONObject.fromObject(content);
					if((int)(jo.getInt("state")) == 1) {
						if((int)(jo.getInt("numbers")) != customer.getUser_numbers()) {
							CustomerBusiness.increment(customer.getUser_name(), (int)(jo.getInt("numbers")));
							continue;
						}
					}
					Thread.sleep(80000); 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			_list = CustomerBusiness.getNameList();
		}
	}
}
