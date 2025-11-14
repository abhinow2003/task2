package thread;

class DownloadChunk extends Thread {
    private String chunkName;

    public DownloadChunk(String chunkName) {
        this.chunkName = chunkName;
    }

    @Override
    public void run() {
        try {
            System.out.println(chunkName + " download started...");
            Thread.sleep(3000); 
            System.out.println(chunkName + " download finished.");
        } catch (InterruptedException e) {
            System.out.println(chunkName + " interrupted.");
        }
    }
}

public class Downloader {
    public static void main(String[] args) {
        DownloadChunk c1 = new DownloadChunk("Chunk 1");
        DownloadChunk c2 = new DownloadChunk("Chunk 2");
        DownloadChunk c3 = new DownloadChunk("Chunk 3");

        c1.start();
        c2.start();
        c3.start();

        try {
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\nDownload complete!");
    }
}

