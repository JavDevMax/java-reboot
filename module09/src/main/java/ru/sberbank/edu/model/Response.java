package ru.sberbank.edu.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Response {

    private MainModel main;
    private WindModel wind;
    private String name;
    private WeatherModel[] weather;

    public Response() {
    }

    public MainModel getMain() {
        return main;
    }

    public void setMain(MainModel main) {
        this.main = main;
    }

    public WindModel getWind() {
        return wind;
    }

    public void setWind(WindModel wind) {
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherModel[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherModel[] weather) {
        this.weather = weather;
    }

}
