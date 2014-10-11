package com.assignment;

/**
 * Bank Account class using ordered locks
 * Created by saba on 2014-10-11.
 */
public class LockAccount implements BankAccount<LockAccount> {
    private Long id;

    //mutable state, yay
    private int availableFunds;

    //Constructor
    public LockAccount(long accountId, int accountAmount) {
        setId(accountId);
        setAvailableFunds(accountAmount);
    }

    // Getters and setters for the above fields.
    public long getId() {
        return this.id;
    }

    public void setId(long accountId){
        this.id = accountId;
    }

    public int getAvailableFunds() {
        return this.availableFunds;
    }

    public void setAvailableFunds(int amount){
        this.availableFunds = amount;
    }

    private void withdraw(int amount) {
        try {
            Thread.sleep(1000L); //simulating DB access
        } catch(InterruptedException e) {}
        availableFunds -= amount;
    }

    private void deposit(int amount) {
        try {
            Thread.sleep(100L); //simulating DB access
        } catch(InterruptedException e) {}
        availableFunds += amount;
    }

    private int compareAccountIds(LockAccount destination) {
        return this.id.compareTo(destination.id);
    }

    public void printAvailableFunds() {
        System.out.println("Account with id " + this.id + " has " + this.availableFunds);
    }
    /**
     * I broke the transferFunds function into deposit and withdraw
     * Lock ordering by account ids - prevents deadlock when two threads are trying
     * to update bank accounts of reverse order
     * @param destination destination account
     * @param amount amount of money to transfer
     */
    public void transferFunds(final LockAccount destination, int amount) {
        LockAccount sourceLock, destLock;

        int result = compareAccountIds(destination);
        //destination account id is greater than source account id
        if (result > 0) {
            sourceLock = destination;
            destLock = this;
        } else {
            sourceLock = this;
            destLock = destination;
        }
        System.out.println("Source lock id " + sourceLock.getId());
        System.out.println("Destination lock id " + destLock.getId());
        synchronized (sourceLock) {
            System.out.println("Source account lock obtained");
            if (this.availableFunds <= amount) {
                throw new IllegalArgumentException("Insufficient funds in source account with ID: " + this.id);
            }
            this.withdraw(amount);
            synchronized (destLock) {
                System.out.println("Destination account lock obtained");
                destination.deposit(amount);
            }

        }

    }
}
