package com.assignment;

/**
 * AccountRunnable class
 * Created by saba on 2014-10-11.
 */
public class AccountRunnable implements Runnable{
    private Account sourceAccount;
    private Account destAccount;
    private int transferAmount;
    private String threadName;

    AccountRunnable(Account source, Account destination, int amount, String name) {
        this.sourceAccount = source;
        this.destAccount = destination;
        this.transferAmount = amount;
        this.threadName = name;
    }

    public void run() {
        System.out.println(threadName + " starting transfer from " + sourceAccount.getId() + " to " + destAccount.getId());
        sourceAccount.transferFunds(destAccount, transferAmount);
    }
}
