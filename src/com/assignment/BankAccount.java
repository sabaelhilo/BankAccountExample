package com.assignment;

/**
 * Created by saba on 2014-10-11.
 */
public interface BankAccount<T> {
    public long getId();
    public void transferFunds(final T destination, int amount);
    public void printAvailableFunds();
}
