package ru.sberbank.edu;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherProvider {

    private RestTemplate restTemplate = null; // ответ от API
    private final String url = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private final String apiKey = "061f7d4de15b8e59fcc916078e008eb5";

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        String url = String.format(this.url, city, apiKey);
        restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            ObjectMapper oMapper = new ObjectMapper();
            Response openWeatherResponse = oMapper.readValue(response.getBody().replace("feels_like","feelsLike"), Response.class);

            return (new WeatherInfo(openWeatherResponse));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
