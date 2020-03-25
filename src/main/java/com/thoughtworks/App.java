package com.thoughtworks;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomePage homePage = new HomePage();
        homePage.initPage();
        homePage.handleChoice(scanner.nextLine());
    }
}
