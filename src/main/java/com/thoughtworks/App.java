package com.thoughtworks;

public class App {

    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        System.out.println(repository.queryAccount("123"));
    }
}
