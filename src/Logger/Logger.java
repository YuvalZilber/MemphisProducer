package Logger;

import producer.SingleProducer;

import java.util.Arrays;

public class Logger {
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SingleProducer.class.getName());

    public static void log(String... line) {
        if ("true".equalsIgnoreCase(System.getProperty("debug"))) {
            StringBuilder sb = new StringBuilder("[" + Thread.currentThread().getName() + "] ");
            int i = 0;
            for (; i < line.length - 1; i++) {
                sb.append("[").append(line[i]).append("] ");
            }
            sb.append(line[i]);
            log.info(sb.toString());
        }
    }
}
