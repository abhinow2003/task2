package ser_obj;

import java.io.*;

class FileExporter {

    private final Object lock = new Object();  // custom lock object

    public void exportObject(Object obj, String fileName) {
        synchronized (lock) {    // only one thread can write at a time (per instance)
            try (ObjectOutputStream oos =
                 new ObjectOutputStream(new FileOutputStream(fileName, true))) {

                System.out.println(Thread.currentThread().getName()
                        + " writing to: " + fileName);

                oos.writeObject(obj);

                System.out.println(Thread.currentThread().getName()
                        + " finished writing: " + fileName);

            } catch (Exception e) {
                System.out.println("Error writing file.");
            }
        }
    }
}

public class FileExporterDemo {
    public static void main(String[] args) {

        FileExporter exporter1 = new FileExporter();
        FileExporter exporter2 = new FileExporter();

        // Same file → synchronized (cannot run at same time)
        Thread t1 = new Thread(() ->
                exporter1.exportObject("Hello", "data1.ser"), "Writer-1");

        Thread t2 = new Thread(() ->
                exporter1.exportObject("World", "data1.ser"), "Writer-2");

        // Different file → parallel execution
        Thread t3 = new Thread(() ->
                exporter2.exportObject("Kochi", "data2.ser"), "Writer-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

