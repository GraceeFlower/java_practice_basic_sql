package com.thoughtworks;

import java.util.Scanner;

public class HomePage {

    private final static String INIT_MESSAGE = "1. 注册\n2. 登录\n3. 退出\n请输入你的选择(1~3)：";
    private final static String SIGN_IN_MESSAGE = "请输入用户名和密码(格式：用户名,密码)：";
    private final static String SIGN_OUT_MESSAGE = "您已退出系统咯～";
    private final static String WRONG_MESSAGE = "没有该选项，请重新选择(1～3)：";

    public HomePage() {
    }

    public void initPage() {
        System.out.println(INIT_MESSAGE);
    }

    public void handleChoice(String choice) {
        switch (choice) {
            case "1":
                new SignUp().initPage();
                break;
            case "2":
                System.out.println(SIGN_IN_MESSAGE);
                break;
            case "3":
                System.out.println(SIGN_OUT_MESSAGE);
                break;
            default:
                System.out.println(WRONG_MESSAGE);
                initPage();
                break;
        }
    }
}
