package com.thoughtworks.repositories;

import com.thoughtworks.entities.Account;
import com.thoughtworks.util.JDBCUtil;

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
            String sql = "SELECT username, phone_num, email, password, query_times, account_state " +
                "FROM account_sys WHERE username = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            result = pre.executeQuery();
            while (result.next()) {
                account.setUserName(result.getString("username"));
                account.setPhoneNumber(result.getString("phone_num"));
                account.setEmail(result.getString("email"));
                account.setPassword(result.getString("password"));
                account.setQueryTimes(result.getInt("query_times"));
                account.setAccountState(result.getString("account_state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(result, pre, conn);
        }
        return account;
    }

    public void saveAccount(Account account) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "INSERT INTO account_sys (username, phone_num, email, password) " +
                "VALUES (?, ?, ?, ?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, account.getUserName());
            pre.setString(2, account.getPhoneNumber());
            pre.setString(3, account.getEmail());
            pre.setString(4, account.getPassword());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(pre, conn);
        }
    }

    public void updateStatement(Account account) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "UPDATE account_sys SET query_times = ?, account_state = ? WHERE username = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, account.getQueryTimes());
            pre.setString(2, account.getAccountState());
            pre.setString(3, account.getUserName());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(pre, conn);
        }
    }
}
