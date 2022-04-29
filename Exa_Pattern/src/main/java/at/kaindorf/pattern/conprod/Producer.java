package at.kaindorf.pattern.conprod;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
    private final List<String> strings;
    private static final String[] values = {"rofl1", "Mayer", "Reinhold", "amk"};

    public Producer(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (!Thread.interrupted()) {
            synchronized (strings) {
                strings.add(values[rand.nextInt(values.length)]);
            }
        }
    }
}