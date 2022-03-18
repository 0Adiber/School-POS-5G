package at.kaindorf.weather.conprod;

import at.kaindorf.weather.beans.Weatherdata;
import at.kaindorf.weather.observer.WeatherDataObserver;
import at.kaindorf.weather.observer.WeatherDataSubject;

import java.util.Queue;

public class WeatherDataConsumer extends WeatherDataSubject implements Runnable {

    private final Queue<Weatherdata> queue;

    public WeatherDataConsumer(Queue<Weatherdata> queue) {
        this.queue = queue;
    }

    private Weatherdata data;

    @Override
    public void run() {
        System.out.println("Consumer started...");
        while (!Thread.interrupted()) {
            synchronized(queue) {
                while(queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        System.out.println("error while waiting on queue");
                    }
                }
                data = queue.poll();
                notifyObservers();
            }
        }

    }

    @Override
    public void notifyObservers() {
        if(data == null) return;
        for(WeatherDataObserver o : observers)
            o.update(data);
    }
}
