//package GetWeatherFromOpenWeatherMapOrg;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        String userDataString = "";
        double userDataDouble = 0;

        WeatherApp weatherapp = new WeatherApp();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Get weather from www.openweathermap.org");

        System.out.println("Latitude=" + weatherapp.getLatitude());
        do {
            System.out.println("Type latitude or 'n' for not change");
            userDataString = userInput.next();
            try {
                userDataDouble = Double.parseDouble(userDataString);
            } catch (NumberFormatException e) {
                if (userDataString.equals("n")) break;
            }
        }
        while (!weatherapp.setLatitude(userDataDouble));

        System.out.println("Longitude=" + weatherapp.getLongitude());
        do {
            System.out.println("Type longitude or 'n' for not change");
            userDataString = userInput.next();
            try {
                userDataDouble = Double.parseDouble(userDataString);
            } catch (NumberFormatException e) {
                if (userDataString.equals("n")) break;
            }
        }
        while (!weatherapp.setLongitude(userDataDouble));

        System.out.println("Current latitude=" + weatherapp.getLatitude());
        System.out.println("Current longitude=" + weatherapp.getLongitude());
        System.out.println("Weather:");

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
