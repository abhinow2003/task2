package thread;
import java.util.Random;

class Stock extends Thread {
    private String name;
    private double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                double change = (random.nextDouble() * 10) - 5;
                price += change;
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " price thread stopped.");
        }
    }

    public String getStockName() {
        return name;
    }
}

public class StockMarketSimulation {
    public static void main(String[] args) {
        Stock s1 = new Stock("TCS", 3500);
        Stock s2 = new Stock("INFY", 1500);
        Stock s3 = new Stock("RELIANCE", 2500);

        s1.start();
        s2.start();
        s3.start();
        int i =0;

        while (i<4) {
            i++;
            System.out.println("\n--- Current Stock Prices ---");
            System.out.println(s1.getStockName() + ": " + s1.getPrice());
            System.out.println(s2.getStockName() + ": " + s2.getPrice());
            System.out.println(s3.getStockName() + ": " + s3.getPrice());
            System.out.println("----------------------------");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Main market loop interrupted.");
            }
        }
    }
}

