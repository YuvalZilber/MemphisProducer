import Logger.Logger;
import producer.MultiProducer;

import java.io.IOException;
import java.util.Arrays;

import static Logger.Logger.log;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        log("args: "+Arrays.toString(args));
        System.setProperty("debug", args[3]);
        MultiProducer multiProducer = new MultiProducer("resources/" +args[0].replace("~/",""));
        log(Arrays.toString(args));
        multiProducer.produce(args[1], Integer.parseInt(args[2]));
    }
}