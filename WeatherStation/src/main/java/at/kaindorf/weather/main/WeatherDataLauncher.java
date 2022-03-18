package at.kaindorf.weather.main;

import at.kaindorf.weather.beans.Weatherdata;
import at.kaindorf.weather.conprod.WeatherDataConsumer;
import at.kaindorf.weather.conprod.WeatherDataProducer;
import at.kaindorf.weather.observer.WeatherDataGUI;
import at.kaindorf.weather.observer.WeatherDataLogger;
import at.kaindorf.weather.observer.WeatherDataPrinter;

import java.util.LinkedList;
import java.util.Queue;

public class WeatherDataLauncher {

    public static void main(String[] args) {
        Queue<Weatherdata> queue = new LinkedList<>();

        WeatherDataProducer producer = new WeatherDataProducer(queue);
        WeatherDataConsumer consumer = new WeatherDataConsumer(queue);

        WeatherDataPrinter printer = new WeatherDataPrinter(consumer);
        WeatherDataLogger logger = new WeatherDataLogger(consumer);
        WeatherDataGUI gui = new WeatherDataGUI(consumer);
        gui.setVisible(true);

        Thread pThread = new Thread(producer, "Producer");
        Thread cThread = new Thread(consumer, "Consumer");

        pThread.start();
        cThread.start();

        try {
            pThread.join(); //wait in this thread until all weather data finished or interrupted
            cThread.interrupt();
        } catch(InterruptedException e) {
            System.out.println("error waiting for producer thread");
        }
    }

}
