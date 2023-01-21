package producer;

import java.io.*;
import java.util.concurrent.CountDownLatch;

public class MultiProducer {
    private final BufferedReader bufferedReader;

    public MultiProducer(String filename) throws FileNotFoundException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        bufferedReader = new BufferedReader(fr);
    }

    public Thread[] produce(String station) {
        return this.produce(station, 1);
    }

    public Thread[] produce(String station, int threadsNumber) {
        return this.produce(station, threadsNumber, true);
    }

    public Thread[] produce(String station, int threads, boolean join) {
        System.setProperty("station", station);
        Thread[] readers = new Thread[threads];
        CountDownLatch latch = new CountDownLatch(threads - 1);
        for (int i = 0; i < threads; i++) {
            readers[i] = new Thread(new SingleProducer(bufferedReader, latch), "Reader_" + i);
        }
        for (Thread thread : readers) {
            thread.start();
        }

        if (join) {
            try {
                for (Thread thread : readers) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return readers;
    }
}
