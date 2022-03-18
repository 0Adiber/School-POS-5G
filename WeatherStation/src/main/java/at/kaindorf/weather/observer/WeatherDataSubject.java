package at.kaindorf.weather.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class WeatherDataSubject {

    protected List<WeatherDataObserver> observers = new ArrayList<>();

    public void register(WeatherDataObserver observer) {
        this.observers.add(observer);
    }

    public void unregister(WeatherDataObserver observer) {
        this.observers.remove(observer);
    }

    public abstract void notifyObservers();

}
