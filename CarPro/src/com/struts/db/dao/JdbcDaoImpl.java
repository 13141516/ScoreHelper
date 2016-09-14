package com.struts.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.struts.utils.ReflectionUtils;

public class JdbcDaoImpl<T> implements DAO<T> {
	private QueryRunner queryRunner = null;
	private Class<T> type = null;

	public JdbcDaoImpl() {
		queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void batch(Connection connection, String sql, Object[]... args) {
		// TODO Auto-generated method stub
		try {
			queryRunner.batch(sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <E> E getForValue(Connection connection, String sql, String columnName, Object... args) {
		// TODO Auto-generated method stub
		try {
			E value = (E) queryRunner.query(connection, sql, new ScalarHandler<E>(columnName), args);
			return value;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T get(Connection connection, String sql, Object... args) {
		// TODO Auto-generated method stub
		try {
			T value = queryRunner.query(connection, sql, new BeanHandler<>(type), args);
			return value;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) {
		// TODO Auto-generated method stub
		try {
			List<T> _List = queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
			return _List;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(Connection connection, String sql, Object... args) {
		// TODO Auto-generated method stub
		try {
			return queryRunner.update(connection, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public <M> List<M> getColumn(Connection connection, String sql, String columnName,Object ...objects) {
		// TODO Auto-generated method stub
		try {
			List<M> _list = (List<M>) queryRunner.query(connection, sql, new ColumnListHandler<M>(columnName),objects);
			return _list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
