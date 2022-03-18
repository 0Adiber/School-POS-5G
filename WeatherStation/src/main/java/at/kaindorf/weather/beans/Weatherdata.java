package at.kaindorf.weather.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Weatherdata {

    @JsonIgnore
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

    private float temperature, pressure, humidity;
    private int windSpeed, windDirection;

    @JsonDeserialize(using = TimestampDeserializer.class)
    @JsonProperty("dateTime")
    private LocalDateTime timestamp;

    public String toCSVLine() {
        return String.format("%s;%.2f;%.01f;%.01f;%d;%d\n", DTF.format(timestamp), temperature, pressure, humidity, windSpeed, windDirection);
    }

}
