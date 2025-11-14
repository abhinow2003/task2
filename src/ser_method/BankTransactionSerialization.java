package ser_method;



import java.io.*;

// Account class (Serializable)
class Account implements Serializable {
    private int accNo;
    private double balance;
    private String type;

    public Account(int accNo, double balance, String type) {
        this.accNo = accNo;
        this.balance = balance;
        this.type = type;
    }
}

// Serializer class
class TransactionSerializer {
    private File file = new File("transactions.ser");

    public synchronized void saveTransaction(Account a) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            System.out.println(Thread.currentThread().getName() + " writing transaction...");
            oos.writeObject(a);
            System.out.println(Thread.currentThread().getName() + " transaction saved.");
        } catch (IOException e) {
            System.out.println("Transaction save error!");
        }
    }
}

public class BankTransactionSerialization {
    public static void main(String[] args) {
        TransactionSerializer ts = new TransactionSerializer();

        Thread t1 = new Thread(() -> {
            ts.saveTransaction(new Account(1001, 5000, "Deposit"));
        }, "T1");

        Thread t2 = new Thread(() -> {
            ts.saveTransaction(new Account(1001, 2000, "Withdrawal"));
        }, "T2");

        t1.start();
        t2.start();
    }
}

