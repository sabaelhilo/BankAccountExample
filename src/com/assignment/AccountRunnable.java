package com.assignment;

/**
 * AccountRunnable class
 * Created by saba on 2014-10-11.
 */
public class AccountRunnable implements Runnable {
    private BankAccount sourceBankAccount;
    private BankAccount destBankAccount;
    private int transferAmount;
    private String threadName;


    AccountRunnable(BankAccount source, BankAccount destination, int amount, String name) {
        this.sourceBankAccount = source;
        this.destBankAccount = destination;
        this.transferAmount = amount;
        this.threadName = name;
    }

    public void run() {
        System.out.println(threadName + " starting transfer from " + sourceBankAccount.getId() + " to " + destBankAccount.getId());
        sourceBankAccount.transferFunds(destBankAccount, transferAmount);
    }
}
