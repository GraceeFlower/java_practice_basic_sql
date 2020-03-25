package com.thoughtworks;

import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository {

    public Account queryAccount(String username) {
        Account account = new Account();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet result = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "SELECT username, phone_num, email, password " +
                "FROM account_sys WHERE username = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            result = pre.executeQuery();
            while (result.next()) {
                account.setUserName(result.getString("username"));
                account.setPhoneNumber(result.getString("phone_num"));
                account.setEmail(result.getString("email"));
                account.setPassword(result.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, pre, result);
        }
        return account;
    }

    public void saveAccount(Account account) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "INSERT INTO account_sys VALUES (?, ?, ?, ?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, account.getUserName());
            pre.setString(2, account.getPhoneNumber());
            pre.setString(3, account.getEmail());
            pre.setString(4, account.getPassword());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, pre);
        }
    }
}
