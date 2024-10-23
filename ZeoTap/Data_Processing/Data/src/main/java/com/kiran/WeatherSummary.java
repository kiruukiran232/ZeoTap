package com.kiran;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weather_summary")
public class WeatherSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double avgTemp;
    private double maxTemp;
    private double minTemp;
    private String dominantCondition;
    private LocalDate date;
    public WeatherSummary() {}

    public WeatherSummary(double avgTemp, double maxTemp, double minTemp, String dominantCondition, LocalDate date) {
        this.avgTemp = avgTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.dominantCondition = dominantCondition;
        this.date = date;
    }
}

