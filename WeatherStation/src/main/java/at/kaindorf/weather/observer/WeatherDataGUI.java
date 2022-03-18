package at.kaindorf.weather.observer;

import at.kaindorf.weather.beans.Weatherdata;

import javax.swing.*;
import java.awt.*;

public class WeatherDataGUI extends JFrame implements WeatherDataObserver{
    private WeatherDataSubject subject;
    private JTextArea ta;

    public WeatherDataGUI(WeatherDataSubject subject) {
        this.subject = subject;
        subject.register(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setSize(300, 300);
        Container container = getContentPane();
        container.setLayout(new GridLayout(1, 1, 4, 4));

        ta = new JTextArea(15, 100);
        JScrollPane sp = new JScrollPane(ta);

        container.add(sp);

        setLocationRelativeTo(null);
    }

    @Override
    public void update(Weatherdata weatherdata) {
        SwingUtilities.invokeLater(() -> ta.append(weatherdata + "\n"));
    }
}
