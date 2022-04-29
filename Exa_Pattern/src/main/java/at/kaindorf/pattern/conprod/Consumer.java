package at.kaindorf.pattern.conprod;

import java.util.List;

public class Consumer implements Runnable {
    public Consumer(List<String> strings) {
        this.strings = strings;
    }

    private List<String> strings;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (strings) {

            }
        }
    }
}