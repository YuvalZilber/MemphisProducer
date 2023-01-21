package producer;

import Memphis.Memphis;
import com.google.gson.Gson;
import messages.Request.ProduceRowRequest;
import messages.Responses.ProduceResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import Logger.Logger;

import static Logger.Logger.log;

public class SingleProducer implements Runnable {
    private final BufferedReader bufferedReader;
    private final CountDownLatch latch;
    private final Memphis memphis = Memphis.getInstance();

    public SingleProducer(BufferedReader bufferedReader, CountDownLatch latch) {
        this.bufferedReader = bufferedReader;
        this.latch = latch;
    }

    @Override
    public void run() {
        String line;
        boolean ready = true;

        try {
            while (ready) {
                synchronized (this.bufferedReader) {
                    line = this.bufferedReader.readLine();
                    ready = this.bufferedReader.ready();
                }
                if (line != null) {
                    if (!ready) latch.await();
                    printLine(line, !ready);
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        latch.countDown();
    }

    private void printLine(String line, boolean last) throws IOException {
        ProduceResponse response = null;

        for (int i = 0; response == null && i < 100; i++) {
            if (i != 0)
                log("retry!");
            ProduceRowRequest message = new ProduceRowRequest(line, last);
            response = memphis.sendData(message, ProduceResponse.class);
        }
    }


}
