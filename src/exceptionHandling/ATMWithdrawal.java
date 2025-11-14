package exceptionHandling;
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class ATMWithdrawal {

    public static void withdraw(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds! Cannot withdraw ₹" + amount);
        }
        System.out.println("Withdrawal successful! Remaining balance: ₹" + (balance - amount));
    }

    public static void main(String[] args) {
        double balance = 5000;
        double withdrawAmount = 7000;

        try {
            withdraw(balance, withdrawAmount);
        } catch (InsufficientFundsException e) {
            System.out.println("ATM Alert: " + e.getMessage());
        }
    }
}


