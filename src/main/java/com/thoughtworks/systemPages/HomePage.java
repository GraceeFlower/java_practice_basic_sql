package com.thoughtworks.systemPages;

import java.util.Scanner;

public class HomePage {

    private final static String INIT_MESSAGE = "1. 注册\n2. 登录\n3. 退出\n请输入你的选择(1~3)：";
    private final static String WRONG_MESSAGE = "没有该选项，请重新选择(1~3)：";
    private static Scanner scanner = new Scanner(System.in);

    public HomePage() {
    }

    public static void initPage() {
        System.out.println(INIT_MESSAGE);
        handleChoice(scanner.nextLine());
    }

    public static void handleChoice(String choice) {
        switch (choice) {
            case "1":
                new SignUpPage().initPage();
                break;
            case "2":
                new SignInPage().initPage();
                break;
            case "3":
                new ExitPage().initPage();
                break;
            default:
                System.out.println(WRONG_MESSAGE);
                initPage();
                break;
        }
    }
}
