package ru.sberbank.edu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.model.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather provider
 */
@Component
public class WeatherProvider {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${apiKey}")
    private String appKey;
    @Value("${weatherUrl}")
    private String url;

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {

        Map<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("appId", appKey);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);

            ObjectMapper oMapper = new ObjectMapper();
            Response openWeatherResponse = oMapper.readValue(response.getBody().replace("feels_like", "feelsLike"), Response.class);

            return (new WeatherInfo(openWeatherResponse));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
