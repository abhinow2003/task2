package ser_class;

import java.io.*;

// Utility class for log writing
class LogWriter {

    // Static synchronized → locks LogWriter.class
    public static synchronized void writeLog(String msg) {
        try (ObjectOutputStream oos =
             new ObjectOutputStream(new FileOutputStream("log.ser", true))) {

            System.out.println(Thread.currentThread().getName() + " writing log...");
            oos.writeObject(msg);
            System.out.println(Thread.currentThread().getName() + " log written.");

        } catch (IOException e) {
            System.out.println("Error writing log.");
        }
    }
}

public class LogWriterSerialization {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            LogWriter.writeLog("User logged in.");
        }, "Logger-1");

        Thread t2 = new Thread(() -> {
            LogWriter.writeLog("System started.");
        }, "Logger-2");

        t1.start();
        t2.start(); // Both threads try to write at same time
    }
}

