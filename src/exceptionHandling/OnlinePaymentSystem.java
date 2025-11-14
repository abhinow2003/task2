package exceptionHandling;

class InvalidCardException extends Exception {
    public InvalidCardException(String message) {
        super(message);
    }
}
public class OnlinePaymentSystem {

    public static void processPayment(String cardNumber) throws InvalidCardException {
        if (cardNumber.length() < 16) {
            throw new InvalidCardException("Card number must be 16 digits!");
        }
        System.out.println("Payment processed successfully!");
    }

    public static void main(String[] args) {
        try {
            String card = "12345678"; 
            processPayment(card);
        } catch (InvalidCardException e) {
            System.out.println("Payment Error: " + e.getMessage());
        }
    }
}
