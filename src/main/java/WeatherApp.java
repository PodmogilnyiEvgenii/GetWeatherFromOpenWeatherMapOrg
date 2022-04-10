//package GetWeatherFromOpenWeatherMapOrg;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Objects;

public class WeatherApp {

    private double latitude = 59.9186;
    private double longitude = 30.4365;
    private String apikey = "f992327b807444ef356b2f6d98e94f23";
    private int responseStatus = 0;
    private int temperature = 0;
    private int pressure = 0;
    private int humidity = 0;
    private int speed = 0;
/*
    public WeatherApp(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
*/
    public WeatherApp() {
    }

    public int getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWildSpeed() {
        return speed;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public boolean setLatitude(double latitude) {
        if (latitude >= -90 & latitude <= 90) {
            this.latitude = latitude;
            return true;
        } else {
            return false;
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean setLongitude(double longitude) {
        if (longitude >= -180 & longitude <= 180) {
            this.longitude = longitude;
            return true;
        } else {
            return false;
        }
    }

    public boolean getWeather() throws InterruptedException, JsonProcessingException {

        String dataResponse = "";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=" + apikey))
                .header("accept", "application/json")
                .timeout(Duration.ofSeconds(5))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            this.responseStatus = response.statusCode();
            dataResponse = response.body();

        } catch (IOException e) {
            System.out.println("Response error");
        }

        if (responseStatus < 300 & !Objects.equals(dataResponse, "")) {

            //System.out.println(response.body());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode weatherData = objectMapper.readTree(dataResponse);

            //System.out.println(weatherData.toPrettyString());

            this.temperature = weatherData.path("main").get("temp").asInt();
            this.pressure = weatherData.path("main").get("pressure").asInt();
            this.humidity = weatherData.path("main").get("humidity").asInt();
            this.speed = weatherData.path("wind").get("speed").asInt();

            return true;
        } else {
            return false;
        }
    }
}
