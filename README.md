BankAccountExample
==================

Java multithreaded bank account example

We need locks because we have a shared mutable variable (available funds). With no locks this could happen: 
Thread 1 : Account source has $100 avail funds wants to transfer to Account destination $30 
Thread 2 : Account source has $100 avail funds wants to transfer to Account destination $80
Thread 1 : Checks if source has >= amount, condition is true then for some reason halts
Thread 2 : Available funds are still at $100 so the condition is true and goes ahead and transfers the money
Thread 1 : Resumes and since it already passed the condition it will deduct the $30 from account x - putting it at a negative amount

Need to add a lock to safely update the mutable object, but might not be as straightforward  

This case:
Thread1: sourceAccount.transferFunds(destinationAccount, 10)
Thread2: destinationAccount.transferFunds(sourceAccount, 20)

Solution 1:
transferFunds could be declared as a synchronized function, but that's not Atomic and locks the whole function 

Solution 2: 
Lock on the source and destination account, so no simultaneous updates happen

Even with those two locks deadlock could happen in this situation. Why?

Thread 1: Obtains lock on source account
Thread 2: Obtains lock on destination account
Thread 1: Asks for lock on destination account but Thread 2 still has it
Thread 2: Asks for lock on source account but Thread 1 still has it
DEADLOCK

-- one solution to the problem would be to provide a global lock object this would solve the deadlock problem but again not ideal
-- what if I use the account id to enforce a lock order? This would prevent the deadlock


    
