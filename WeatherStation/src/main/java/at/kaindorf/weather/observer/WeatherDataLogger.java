package at.kaindorf.weather.observer;

import at.kaindorf.weather.beans.Weatherdata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WeatherDataLogger implements WeatherDataObserver{

    private final WeatherDataSubject subject;
    private static final Path outPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weather.log");

    static {
        File outFile = outPath.toFile();
        try {
            if (outFile.exists()) {
                outFile.delete();
            }

            outFile.createNewFile();
            Files.write(outPath, "timestamp;temperature;pressure;humidity;windspeed;windDirection\n".getBytes());
        } catch (IOException e) {
            System.out.println("error creating logger file");
        }
    }

    public WeatherDataLogger(WeatherDataSubject subject) {
        this.subject = subject;
        subject.register(this);
    }

    @Override
    public void update(Weatherdata weatherdata) {
        try {
            Files.write(outPath, weatherdata.toCSVLine().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("error writing");
        }
    }
}
