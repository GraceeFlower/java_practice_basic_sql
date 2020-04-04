package com.thoughtworks.repositories;

import com.thoughtworks.entities.Account;
import com.thoughtworks.util.JDBCUtil;
import com.thoughtworks.util.SqlUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountRepository {

    public Account queryAccount(String username) {
        Account account = new Account();
        try {
            Connection conn = JDBCUtil.connectToDB();
            String sql = "SELECT username, phoneNumber, email, password, queryTimes, accountState FROM account_sys WHERE username = ?";
            account = SqlUtil.executeQuerySingle(conn, sql, account.getClass(), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void saveAccount(Account account) {
        try {
            Connection conn = JDBCUtil.connectToDB();
            String sql = "INSERT INTO account_sys (username, phoneNumber, email, password) VALUES (?, ?, ?, ?)";
            SqlUtil.executeUpdate(conn, sql,
                account.getUserName(), account.getPhoneNumber(), account.getEmail(), account.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatement(Account account) {
        try {
            Connection conn = JDBCUtil.connectToDB();
            String sql = "UPDATE account_sys SET queryTimes = ?, accountState = ? WHERE username = ?";
            SqlUtil.executeUpdate(conn, sql,
                account.getQueryTimes(), account.getAccountState(), account.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
