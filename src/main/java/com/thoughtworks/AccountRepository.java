package com.thoughtworks;

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
}
