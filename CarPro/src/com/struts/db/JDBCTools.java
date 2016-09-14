package com.struts.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCTools {
	/*
	 * 数据连接池初始化
	 * */
	private static ComboPooledDataSource dataSource = null;
	static {
		dataSource=new ComboPooledDataSource("MySql_c3p0");
	}
	/*
	 * 事务
	 * */
	public static void startTransanction(Connection mConnection) {
		if(mConnection!=null) {
			try {
				mConnection.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void commitTransanction(Connection mConnection) {
		if(mConnection!=null) {
			try {
				mConnection.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void rollbackTransanction(Connection mConnection) {
		if(mConnection!=null) {
			try {
				mConnection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * 获得连接
	 * */
	public static Connection getConnection() {
		try {
			Connection mConnection =dataSource.getConnection();
			return mConnection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 释放链接
	 * */
	public static void realseConnection(Connection mConnection) {
		if(mConnection!=null) {
			try {
				mConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
