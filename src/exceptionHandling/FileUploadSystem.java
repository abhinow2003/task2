package exceptionHandling;
import java.io.*;
 class FileUploadException extends Exception {
    public FileUploadException(String message) {
        super(message);
    }
}
public class FileUploadSystem {

    public static void main(String[] args) {

        FileInputStream fis = null;

        try {
            File file = new File("user_upload.txt");

            if (!file.exists()) {
                throw new FileUploadException("Uploaded file does not exist!");
            }

            fis = new FileInputStream(file); 
            int data;

            while ((data = fis.read()) != -1) {
                System.out.print((char) data);
            }

        } catch (FileUploadException e) {
            System.out.println("Upload Error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        } catch (SecurityException e) {
            System.out.println("Error: No permission to read the file!");
        } catch (IOException e) {
            System.out.println("Error: Could not read the file!");
        } finally {
            try {
                if (fis != null)
                    fis.close();
                System.out.println("\nFile closed successfully (finally block).");
            } catch (IOException e) {
                System.out.println("Error closing the file!");
            }
        }
    }
}


