package com.thoughtworks;

public class App {

    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        System.out.println(repository.queryAccount("123"));
        repository.saveAccount(
            new Account("222", "13456789238", "123@qq.com", "234"));
    }
}
