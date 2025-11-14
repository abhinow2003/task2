package ser_obj;

import java.io.*;

// Serializable report
class PatientReport implements Serializable {
    String reportId;

    public PatientReport(String reportId) {
        this.reportId = reportId;
    }
}

class ReportExporter {

    public void exportReport(PatientReport report) {
        // Locking ONLY the report object
        synchronized (report) {
            try (ObjectOutputStream oos =
                 new ObjectOutputStream(new FileOutputStream(report.reportId + ".ser"))) {

                System.out.println(Thread.currentThread().getName()
                        + " exporting report: " + report.reportId);

                oos.writeObject(report);

                System.out.println(Thread.currentThread().getName()
                        + " finished report: " + report.reportId);

            } catch (Exception e) {
                System.out.println("Error exporting report.");
            }
        }
    }
}

public class PatientReportSerialization {
    public static void main(String[] args) {

        ReportExporter exporter = new ReportExporter();

        PatientReport r1 = new PatientReport("Report_101");
        PatientReport r2 = new PatientReport("Report_102");

        // Threads using different report objects → RUN PARALLEL
        Thread t1 = new Thread(() -> exporter.exportReport(r1), "T1");
        Thread t2 = new Thread(() -> exporter.exportReport(r2), "T2");

        // Threads using same report → MUST WAIT
        Thread t3 = new Thread(() -> exporter.exportReport(r1), "T3");

        t1.start();
        t2.start();
        t3.start();
    }
}

