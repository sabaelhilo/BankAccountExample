package com.assignment;

public class Main {

    public static void main(String[] args) {

        //Two example accounts
        Account sourceAccount = new Account(1L, 100);
        Account destinationAccount = new Account(2L, 80);

        Thread t1 = new Thread(new AccountRunnable(sourceAccount, destinationAccount, 10, "Thread1"));
        Thread t2 = new Thread(new AccountRunnable(destinationAccount, sourceAccount, 20, "Thread2"));
        Thread t3 = new Thread(new AccountRunnable(destinationAccount, sourceAccount, 5, "Thread3"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException e){}

        System.out.println("All threads are done");
        sourceAccount.printAvailableFunds();
        destinationAccount.printAvailableFunds();

    }

}
