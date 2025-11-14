package thread;

class StatusUpdater extends Thread {
    private String[] status = {
        "Order Placed", "Restaurant Accepted", "Preparing Food",
        "Out for Delivery", "Nearby", "Delivered"
    };

    @Override
    public void run() {
        try {
            for (String s : status) {
                System.out.println("Status Update: " + s);
                Thread.sleep(5000); // 5 seconds delay
            }
        } catch (InterruptedException e) {
            System.out.println("Status Updater Interrupted");
        }
    }
}

class ETAThread extends Thread {
    @Override
    public void run() {
        try {
            int eta = 30;
            while (eta > 0) {
                System.out.println("Estimated Delivery Time: " + eta + " mins");
                Thread.sleep(5000); // print every 5 seconds
                eta -= 5;
            }
        } catch (InterruptedException e) {
            System.out.println("ETA Thread Interrupted");
        }
    }
}

public class FoodDeliveryApp {
    public static void main(String[] args) {
        StatusUpdater statusThread = new StatusUpdater();
        ETAThread etaThread = new ETAThread();

        statusThread.start();
        etaThread.start();
    }
}

