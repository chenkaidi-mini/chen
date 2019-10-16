package com.gp4.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ckd 2019/9/10 14:19
 */
public class DataSourceUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal;

    static {
        Properties properties = new Properties();
        try {
            threadLocal=new ThreadLocal<>();
            properties.load(DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化连接池失败");
        }
    }
    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null){
            conn = dataSource.getConnection();
            threadLocal.set(conn);
        }
        return conn;
    }

    public static void startTransaction() throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        Connection conn = getConnection();
        conn.commit();
    }

    public static void rollback() throws SQLException {
        Connection conn = getConnection();
        conn.rollback();
    }

    public static void close() throws SQLException {
        Connection conn = getConnection();
        conn.close();
        threadLocal.remove();
    }
}
