package com.thoughtworks;

public class App {

    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        System.out.println(repository.queryAccount("123"));
        Account account = new Account("222", "13456789238", "123@qq.com", "234");
        repository.saveAccount(account);
        account.setQueryTimes(account.getQueryTimes() + 1);
        account.setAccountState("locked");
        repository.updateStatement(account);
    }
}
