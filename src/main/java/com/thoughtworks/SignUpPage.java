package com.thoughtworks;

import java.util.Scanner;

public class SignUpPage {

    private final static String SIGN_UP_MESSAGE = "请输入注册信息(格式：用户名,手机号,邮箱,密码)：";
    private final static String SIGN_UP_SUCCESSFULLY = "%s，恭喜你注册成功！";
    private final static String WRONG_FORMAT = "格式错误\n请按正确格式输入注册信息：";
    private final static String SIGN_UP_FAILED = "%s不合法\n请输入合法的注册信息：";
    private Scanner scanner = new Scanner(System.in);
    private AccountRepository repository = new AccountRepository();

    public SignUpPage() {
    }

    public void initPage() {
        System.out.println(SIGN_UP_MESSAGE);
        String registerMsg = scanner.nextLine();
        checkFormat(registerMsg);
    }

    public void checkFormat(String registerMsg) {
        String[] registerArr = registerMsg.split(",");
        FormatChecker formatChecker = new FormatChecker(registerArr);
        if (4 != registerArr.length) {
            System.out.println(WRONG_FORMAT);
            initPage();
        } else if(formatChecker.isCorrect()) {
            handleCorrectMsg(registerArr);
            System.out.println(String.format(SIGN_UP_SUCCESSFULLY, registerArr[0]));
        } else {
            System.out.println(String.format(
                SIGN_UP_FAILED, formatChecker.getFirstErrorMsg()));
            initPage();
        }
    }

    private void handleCorrectMsg(String[] msg) {
        repository.saveAccount(new Account(msg[0], msg[1], msg[2], msg[3]));
    }
}
