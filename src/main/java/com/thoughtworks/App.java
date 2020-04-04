package com.thoughtworks;

//import com.thoughtworks.entities.Account;
import com.thoughtworks.systemPages.HomePage;
//import com.thoughtworks.util.JDBCUtil;
//import com.thoughtworks.util.SqlUtil;

//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

public class App {

    public static void main(String[] args) {
        HomePage.initPage();
//        System.out.println(queryAllUser());
    }

//    public static List<Account> queryAllUser() {
//        Connection conn = null;
//        List<Account> userList = new ArrayList<>();
//        try {
//            conn = JDBCUtil.connectToDB();
//            String sql = "SELECT username, phoneNumber, email, password FROM account_sys";
//            userList = SqlUtil.executeQueryAll(conn, sql, Account.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.releaseSource(null, null, conn);
//        }
//        return userList;
//    }
}
