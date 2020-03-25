package com.thoughtworks;

import java.util.Scanner;

public class SignInPage {

    private final static String INIT_MESSAGE = "请输入用户名和密码(格式：用户名,密码)：";
    private final static String WRONG_FORMAT = "格式错误\n请按正确格式输入用户名和密码：";
    private final static String INVALID_ACCOUNT = "密码或用户名错误\n请重新输入用户名和密码：";
    private final static String LOCK_ACCOUNT = "您已3次输错密码，账号被锁定";
    private final static String SIGN_IN_SUCCESSFULLY = "%s，欢迎回来！\n您的手机号是%s，邮箱是%s\n";
    private final static String LOCKED = "locked";
    private final static String ACTIVE = "active";
    private final static Scanner scanner = new Scanner(System.in);
    private AccountRepository repository = new AccountRepository();

    public void initPage() {
        System.out.println(INIT_MESSAGE);
        String accountMsg = scanner.nextLine();
        checkFormat(accountMsg);
    }

    private void checkFormat(String account) {
        String[] accountArr = account.split(",");
        if (2 != accountArr.length) {
            handleWrongInput(WRONG_FORMAT);
        } else {
            checkAccount(accountArr);
        }
    }

    private void checkAccount(String[] accountArr) {
        Account account = repository.queryAccount(accountArr[0]);
        if (null != account && !LOCKED.equals(account.getAccountState())) {
            account.setQueryTimes(account.getQueryTimes() + 1);
            repository.updateStatement(account);
            if (accountArr[0].equals(account.getUserName())
                && accountArr[1].equals(account.getPassword())) {
                handleCorrectInput(account);
            } else if (3 == account.getQueryTimes()) {
                handleLockedUser(account);
            } else {
                handleWrongInput(INVALID_ACCOUNT);
            }
        } else if (null == account) {
            handleWrongInput(INVALID_ACCOUNT);
        } else {
            System.out.println(LOCK_ACCOUNT);
            HomePage.initPage();
        }
    }

    private void handleCorrectInput(Account account) {
        System.out.println(String.format(SIGN_IN_SUCCESSFULLY,
            account.getUserName(),
            account.getPhoneNumber(),
            account.getEmail()));
        account.setQueryTimes(0);
        repository.updateStatement(account);
        HomePage.initPage();
    }

    private void handleLockedUser(Account account) {
        System.out.println(LOCK_ACCOUNT);
        account.setAccountState(LOCKED);
        account.setQueryTimes(3);
        repository.updateStatement(account);
        HomePage.initPage();
    }

    private void handleWrongInput(String instructions) {
        System.out.println(instructions);
        initPage();
    }
}
