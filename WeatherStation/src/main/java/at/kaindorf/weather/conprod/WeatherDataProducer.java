package at.kaindorf.weather.conprod;

import at.kaindorf.weather.beans.Weatherdata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class WeatherDataProducer implements Runnable {

    private final Queue<Weatherdata> queue;
    private List<Weatherdata> data;

    public WeatherDataProducer(Queue<Weatherdata> queue) {
        this.queue = queue;
        //read shit
        try {
            data = mapper.readValue(Path.of("src", "main", "resources", "weatherdata.json").toFile(), new TypeReference<List<Weatherdata>>() {
            });
        } catch (IOException e) {
            System.out.println("ERROR reading data!");
            Thread.currentThread().interrupt();
        }
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void run() {

        LocalDateTime previous = null;

        while(!Thread.interrupted() || data.size() == 0) {
            Weatherdata entry = data.get(0);
            data.remove(0);
            if (previous != null) {
                Duration duration = Duration.between(previous, entry.getTimestamp());
                try {
                    Thread.sleep(duration.getSeconds() * 1000L);
                } catch (InterruptedException e) {
                    System.out.println("ERROR: could not sleep in producer");
                }
            }

            synchronized (queue) {
                queue.add(entry);
                queue.notify();
            }
            previous = entry.getTimestamp();
        }
    }
}
