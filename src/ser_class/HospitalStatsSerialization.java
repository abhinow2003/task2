package ser_class;
import java.io.*;

// Global stats class
class HospitalStats implements Serializable {
    static int patientCount = 0;

    // Static synchronized method → class-level lock
    public static synchronized void saveStats() {
        try (ObjectOutputStream oos =
             new ObjectOutputStream(new FileOutputStream("stats.ser"))) {

            System.out.println(Thread.currentThread().getName() +
                               " saving stats: patientCount=" + patientCount);

            HospitalStats s = new HospitalStats(); // dummy serialization object
            oos.writeObject(s);

            System.out.println(Thread.currentThread().getName() + " stats saved.");

        } catch (IOException e) {
            System.out.println("Error saving stats.");
        }
    }
}

public class HospitalStatsSerialization {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            HospitalStats.patientCount++;
            HospitalStats.saveStats();
        }, "Stats-Thread-1");

        Thread t2 = new Thread(() -> {
            HospitalStats.patientCount++;
            HospitalStats.saveStats();
        }, "Stats-Thread-2");

        t1.start();
        t2.start();
    }
}

