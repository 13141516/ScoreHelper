package com.struts.db.dao;

import java.sql.Connection;
import java.util.List;

public interface DAO<T> {
	/**
	 * 批处理
	 * @param connection
	 * @param sql
	 * @param args
	 */
	void batch(Connection connection, String sql, Object [] ... args);
	/**
	 * 查询 ，返回一个值，一个对象，一个对象链表，即对应数据库的select操作。
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	<E> E getForValue(Connection connection, String sql, String columnName, Object ... args);
	/**
	 * @param connection
	 * @param sql
	 * @param columnName
	 * @return 返回列名为columnName的一列值
	 */
	<M> List<M> getColumn(Connection connection, String sql, String columnName,Object ...args); 
	T get(Connection connection, String sql, Object ... args);
	List<T> getForList(Connection connection, String sql, Object ... args);
	/**
	 * 更新，对应数据库的insert ,update ,delete操作。
	 * @param connection
	 * @param sql
	 * @param args
	 */
	int update(Connection connection, String sql, Object ... args);
}
