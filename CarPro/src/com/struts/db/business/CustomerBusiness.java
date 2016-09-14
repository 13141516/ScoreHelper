package com.struts.db.business;

import java.sql.Connection;
import java.util.List;

import com.struts.db.JDBCTools;
import com.struts.db.dao.JdbcDaoImpl;
import com.struts.model.CusOther;
import com.struts.model.Customer;

class CustomerDao extends JdbcDaoImpl<Customer> {
}
class CusOtherrDao extends JdbcDaoImpl<CusOther> {
}

public class CustomerBusiness {
	private static CustomerDao cd = new CustomerDao();
	private static CusOtherrDao cod = new CusOtherrDao();
	
	/**
	 * insert a record
	 * @param customer
	 * @return
	 */
	public static int insertItem(Customer customer) {
		String sql = "insert into customer(user_name,user_password,user_mail) values(?,?,?)";
		Connection mConnection = JDBCTools.getConnection();
		int flag = cd.update(mConnection, sql, customer.getUserName(), customer.getUserPassword(), customer.getUserEmail());
		JDBCTools.realseConnection(mConnection);
		return flag;
	}
	
	public static int getNumber(String userName) {
		String sql = "select user_numbers from customer where user_name=?";
		Connection mConnection = JDBCTools.getConnection();
		int flag = cd.getForValue(mConnection, sql, "user_numbers", userName);
		JDBCTools.realseConnection(mConnection);
		return flag;
	}
	
	public static int increment(String userName, int numbers) {
		String sql  = "update customer set user_numbers=?  where user_name=?";
		Connection mConnection = JDBCTools.getConnection();
		int flag = cd.update(mConnection, sql, numbers, userName);
		JDBCTools.realseConnection(mConnection);
		return flag;
	}
	
	public static Long getNumbers() {
		String sql = "select count(*) number from customer";
		Connection mConnection = JDBCTools.getConnection();
		Long account = cd.getForValue(mConnection, sql, "number");
		JDBCTools.realseConnection(mConnection);
		return account;
	}
	
	public static CusOther getCustomer(String userName) {
		String sql = "select user_name ,user_password,user_mail,user_numbers from customer where user_name=?";
		Connection mConnection = JDBCTools.getConnection();
		List<CusOther> _List = cod.getForList(mConnection, sql, userName);
		JDBCTools.realseConnection(mConnection);
		if(_List.size() == 1) {
			return _List.get(0);
		} else {
			return null;
		}
	} 
	
	public static List<String> getNameList() {
		String sql = "select user_name from customer";
		Connection mConnection = JDBCTools.getConnection();
		List<String> result = cd.getColumn(mConnection, sql, "user_name");
		JDBCTools.realseConnection(mConnection);
		return result;
	}
	
}
