package com.thoughtworks.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUtil {

    public static void executeUpdate(Connection conn, String sql, Object... args) {
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pre.setObject(i + 1, args[i]);
            }
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(null, pre, conn);
        }
    }

    public static <T> T executeQuerySingle(Connection conn, Class<T> clazz, String sql, Object... args) {
        PreparedStatement pre = null;
        ResultSet rs = null;
        T t = null;
        try {
            pre = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pre.setObject(i + 1, args[i]);
            }
            rs = pre.executeQuery();
            ResultSetMetaData resultSetMD = rs.getMetaData();
            int columnCount = resultSetMD.getColumnCount();
            if (rs.next()) {
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = resultSetMD.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(columnName);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(rs, pre, conn);
        }
        return t;
    }

    public static <T> List<T> executeQueryAll(Connection conn, String sql, Class<T> clazz, Object... args) {
        PreparedStatement pre = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            pre = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pre.setObject(i + 1, args[i]);
            }
            rs = pre.executeQuery();
            ResultSetMetaData resultSetMD = rs.getMetaData();
            int columnCount = resultSetMD.getColumnCount();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = resultSetMD.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(columnName);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(rs, pre, conn);
        }
        return list;
    }
}
