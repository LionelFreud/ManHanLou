package com.yyedu.dao;


import com.yyedu.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

public class BasicDao<T> {
    private QueryRunner qr = new QueryRunner();
    public  int update(String sql,Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.Close(null, null, connection);
        }
    }
    public List<T> queryMulti(String sql,Class <T>cls, Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            List<T> query = qr.query(connection, sql, new BeanListHandler<>(cls), parameters);
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.Close(null, null, connection);
        }
    }
    public T querySingle(String sql,Class <T>cls, Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            T query = qr.query(connection, sql, new BeanHandler<>(cls), parameters);
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.Close(null, null, connection);
        }
    }
    public Object queryScalar(String sql, Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            Object query = qr.query(connection, sql, new ScalarHandler<>(), parameters);
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.Close(null, null, connection);
        }
    }
}
