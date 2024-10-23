package com.kiran;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

public class WeatherDataFetcher implements Runnable {
    private static final String API_KEY = "fabd9158e859c206320f49244b31b18c";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private static final String[] CITIES = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};

    @Override
    public void run() {
        for (String city : CITIES) {
            try {
                String apiUrl = String.format(API_URL, city, API_KEY);
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                Scanner sc = new Scanner(url.openStream());
                StringBuilder json = new StringBuilder();
                while (sc.hasNext()) {
                    json.append(sc.nextLine());
                }
                sc.close();

                // Parse the JSON response
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(json.toString());
                double tempInKelvin = rootNode.path("main").path("temp").asDouble();
                double tempInCelsius = tempInKelvin - 273.15;
                saveWeatherData(tempInCelsius, city);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveWeatherData(double temp, String city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        WeatherSummary summary = new WeatherSummary(temp, temp, temp, "Clear", LocalDate.now());
        session.save(summary);
        transaction.commit();
        session.close();
    }
}
