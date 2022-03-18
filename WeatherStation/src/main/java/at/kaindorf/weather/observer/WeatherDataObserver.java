package at.kaindorf.weather.observer;

import at.kaindorf.weather.beans.Weatherdata;

public interface WeatherDataObserver {

    void update(Weatherdata weatherdata);

}
