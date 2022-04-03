/*
 * This Java source file was generated by the Gradle 'init' task.
 */
//package GetWeatherFromOpenWeatherMapOrg;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Get weather from openweathermap.org");
        WeatherApp weatherapp = new WeatherApp();

        if (weatherapp.getWeather()) {
            System.out.println("Response Status: " + weatherapp.getResponseStatus());
            System.out.println("Temperature: " + weatherapp.getTemperature());
            System.out.println("Pressure: " + weatherapp.getPressure());
            System.out.println("Humidity: " + weatherapp.getHumidity());
            System.out.println("Wild Speed: " + weatherapp.getWildSpeed());

        } else {
            System.out.println("Error: " + weatherapp.getResponseStatus());

        }

    }
}
