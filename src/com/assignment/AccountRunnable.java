package com.assignment;

/**
 * AccountRunnable class
 * Created by saba on 2014-10-11.
 */
public class AccountRunnable<T extends BankAccount> implements Runnable {
    private T sourceBankAccount;
    private T destBankAccount;
    private int transferAmount;
    private String threadName;


    AccountRunnable(T source, T destination, int amount, String name) {
        this.sourceBankAccount = source;
        this.destBankAccount = destination;
        this.transferAmount = amount;
        this.threadName = name;
    }

    public void run() {
        System.out.println(threadName + " starting transfer from " + sourceBankAccount.getId() + " to " + destBankAccount.getId());
        //need to check for types somehow

        sourceBankAccount.transferFunds(destBankAccount, transferAmount);

    }
}
