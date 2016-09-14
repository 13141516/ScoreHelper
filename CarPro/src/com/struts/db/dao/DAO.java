package com.struts.db.dao;

import java.sql.Connection;
import java.util.List;

public interface DAO<T> {
	/**
	 * ������
	 * @param connection
	 * @param sql
	 * @param args
	 */
	void batch(Connection connection, String sql, Object [] ... args);
	/**
	 * ��ѯ ������һ��ֵ��һ������һ��������������Ӧ���ݿ��select������
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
	 * @return ��������ΪcolumnName��һ��ֵ
	 */
	<M> List<M> getColumn(Connection connection, String sql, String columnName,Object ...args); 
	T get(Connection connection, String sql, Object ... args);
	List<T> getForList(Connection connection, String sql, Object ... args);
	/**
	 * ���£���Ӧ���ݿ��insert ,update ,delete������
	 * @param connection
	 * @param sql
	 * @param args
	 */
	int update(Connection connection, String sql, Object ... args);
}
