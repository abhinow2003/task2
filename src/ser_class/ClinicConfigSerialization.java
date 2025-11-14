package ser_class;

import java.io.*;

// Class with STATIC configuration data
class ClinicConfig implements Serializable {
    static String hospitalName = "City Clinic";
    static String location = "Kochi";

    // Static synchronized serialization method
    public static synchronized void saveConfig() {
        try (ObjectOutputStream oos =
             new ObjectOutputStream(new FileOutputStream("config.ser"))) {

            System.out.println(Thread.currentThread().getName() + " saving config...");
            ClinicConfig obj = new ClinicConfig();  // dummy object for serialization
            oos.writeObject(obj);
            System.out.println(Thread.currentThread().getName() + " config saved.");

        } catch (IOException e) {
            System.out.println("Error saving config.");
        }
    }
}

public class ClinicConfigSerialization {
    public static void main(String[] args) {

        // Thread 1
        Thread t1 = new Thread(() -> {
            ClinicConfig.saveConfig();
        }, "Thread-1");

        // Thread 2
        Thread t2 = new Thread(() -> {
            ClinicConfig.saveConfig();
        }, "Thread-2");

        t1.start();
        t2.start(); // Both try to call STATIC synchronized method
    }
}

