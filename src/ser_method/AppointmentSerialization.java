package ser_method;



import java.io.*;

// Appointment class
class Appointment implements Serializable {
    private String doctor;
    private String patient;
    private String time;

    public Appointment(String doctor, String patient, String time) {
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
    }
}

// Serializer
class AppointmentSerializer {
    private File file = new File("appointments.ser");

    public synchronized void saveAppointment(Appointment a) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            System.out.println(Thread.currentThread().getName() + " booking appointment...");
            oos.writeObject(a);
            System.out.println(Thread.currentThread().getName() + " appointment saved.");
        } catch (IOException e) {
            System.out.println("Error saving appointment!");
        }
    }
}

public class AppointmentSerialization {
    public static void main(String[] args) {
        AppointmentSerializer as = new AppointmentSerializer();

        Thread t1 = new Thread(() -> {
            as.saveAppointment(new Appointment("Dr. Suresh", "Abhinav", "10:00 AM"));
        }, "Booking-1");

        Thread t2 = new Thread(() -> {
            as.saveAppointment(new Appointment("Dr. Suresh", "Rahul", "10:15 AM"));
        }, "Booking-2");

        t1.start();
        t2.start();
    }
}

