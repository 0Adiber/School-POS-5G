package at.kaindorf.weather.observer;

import at.kaindorf.weather.beans.Weatherdata;

import java.time.LocalDateTime;

public class WeatherDataPrinter implements WeatherDataObserver{

    private WeatherDataSubject subject;

    public WeatherDataPrinter(WeatherDataSubject subject) {
        this.subject = subject;
        subject.register(this);
    }

    @Override
    public void update(Weatherdata weatherdata) {
        System.out.println(LocalDateTime.now() + " new: " + weatherdata);
    }
}
