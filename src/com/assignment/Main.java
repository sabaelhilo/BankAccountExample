package com.assignment;

public class Main {

    public static void main(String[] args) {

        //Two example accounts using locks
        LockAccount sourceLockAccount = new LockAccount(1L, 100);
        LockAccount destinationLockAccount = new LockAccount(2L, 80);

        Thread t1 = new Thread(new AccountRunnable<LockAccount>(sourceLockAccount, destinationLockAccount, 10, "Thread1"));
        Thread t2 = new Thread(new AccountRunnable<LockAccount>(destinationLockAccount, sourceLockAccount, 20, "Thread2"));
        Thread t3 = new Thread(new AccountRunnable<LockAccount>(destinationLockAccount, sourceLockAccount, 5, "Thread3"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException e){}

        System.out.println("All threads are done");
        sourceLockAccount.printAvailableFunds();
        destinationLockAccount.printAvailableFunds();


        //Two example accounts using transactions (multiverse's implementation of STM)
        TransactionalAccount sourceTxnAccount = new TransactionalAccount(1L, 100);
        TransactionalAccount destTxnAccount = new TransactionalAccount(2L, 80);

        t1 = new Thread(new AccountRunnable<TransactionalAccount>(sourceTxnAccount, destTxnAccount, 10, "Thread1"));
        t2 = new Thread(new AccountRunnable<TransactionalAccount>(destTxnAccount, sourceTxnAccount, 20, "Thread2"));
        t3 = new Thread(new AccountRunnable<TransactionalAccount>(destTxnAccount, sourceTxnAccount, 5, "Thread3"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException e){}

        System.out.println("All threads are done");
        sourceTxnAccount.printAvailableFunds();
        destTxnAccount.printAvailableFunds();


    }

}
