package com.assignment;
import org.multiverse.api.references.*;
import static org.multiverse.api.StmUtils.*;

/**
 * Bank Account class using transactions instead of locks
 * Created by saba on 2014-10-11.
 */
public class TransactionalAccount implements BankAccount<TransactionalAccount> {

    private TxnInteger availableFunds;
    private long id;

    public TransactionalAccount(long accountId, int balance){
        this.availableFunds = newTxnInteger(balance);
        this.id = accountId;
    }

    // Getters and setters for the above fields.
    public long getId() {
        return this.id;
    }

    public void setId(long accountId){
        this.id = accountId;
    }

    public TxnInteger getAvailableFunds() {
        return this.availableFunds;
    }

    public void setAvailableFunds(TxnInteger amount){
        this.availableFunds = amount;
    }

    private void withdraw(int amount) {
        try {
            Thread.sleep(1000L); //simulating DB access
        } catch(InterruptedException e) {}
        this.availableFunds.decrement(amount);
    }

    private void deposit(int amount) {
        try {
            Thread.sleep(100L); //simulating DB access
        } catch(InterruptedException e) {}
        this.availableFunds.increment(amount);
    }

    public void printAvailableFunds() {
        System.out.println("Account with id " + this.id + " has " + this.availableFunds.atomicToString());
    }

    public void transferFunds(final TransactionalAccount destination,final int amount) {
        final TransactionalAccount source = this;
        atomic(new Runnable() {
            @Override
            public void run() {
                source.withdraw(amount);
                destination.deposit(amount);
            }
        });
    }
}
