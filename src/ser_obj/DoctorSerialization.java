package ser_obj;

import java.io.*;

// Doctor class (Serializable)
class Doctor implements Serializable {
    private String name;
    private String dept;

    public Doctor(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }

    // Instance-level synchronized method → lock is on THIS object
    public synchronized void serializeDoctor() {
        try (ObjectOutputStream oos =
             new ObjectOutputStream(new FileOutputStream(name + ".ser"))) {

            System.out.println(Thread.currentThread().getName()
                    + " serializing doctor: " + name);

            oos.writeObject(this);

            System.out.println(Thread.currentThread().getName()
                    + " finished serializing: " + name);

        } catch (Exception e) {
            System.out.println("Error serializing doctor.");
        }
    }
}

public class DoctorSerialization {
    public static void main(String[] args) {

        Doctor d1 = new Doctor("Dr_Ajay", "Cardiology");
        Doctor d2 = new Doctor("Dr_Ravi", "Neurology");

        // Threads operating on different doctor objects → NO blocking
        Thread t1 = new Thread(d1::serializeDoctor, "Thread-1");
        Thread t2 = new Thread(d2::serializeDoctor, "Thread-2");

        t1.start();
        t2.start();
    }
}

