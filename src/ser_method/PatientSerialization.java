package ser_method;

import java.io.*;


class Patient implements Serializable {
    private String name;
    private int age;
    private String disease;

    public Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }
}

// Serializer class with synchronized method
class PatientSerializer {
    private File file = new File("patients.ser");

    public synchronized void savePatient(Patient p) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            System.out.println(Thread.currentThread().getName() + " is saving a patient...");
            oos.writeObject(p);
            System.out.println(Thread.currentThread().getName() + " saved patient successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving patient!");
        }
    }
}

public class PatientSerialization {
    public static void main(String[] args) {
        PatientSerializer serializer = new PatientSerializer();

        Thread t1 = new Thread(() -> {
            serializer.savePatient(new Patient("Abhinav", 22, "Fever"));
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            serializer.savePatient(new Patient("Rahul", 30, "Cold"));
        }, "Thread-2");

        t1.start();
        t2.start();
    }
}
