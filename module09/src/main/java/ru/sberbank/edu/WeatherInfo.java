package ru.sberbank.edu;

import ru.sberbank.edu.model.Response;
import ru.sberbank.edu.model.WeatherInfoModel;
import java.time.LocalDateTime;

/**
 * Weather info.
 */
public class WeatherInfo {

    private static final int FIVE_MINUTES = 5;
    private String city;

    /**
     * Short weather description
     * Like 'sunny', 'clouds', 'raining', etc
     */
    private String shortDescription;

    /**
     * Weather description.
     * Like 'broken clouds', 'heavy raining', etc
     */
    private String description;

    /**
     * Temperature.
     */
    private double temperature;

    /**
     * Temperature that fells like.
     */
    private double feelsLikeTemperature;

    /**
     * Wind speed.
     */
    private double windSpeed;

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime;

    /***
     * конструктор с параметром - класс структура ответа от api.openweathermap.org
     * @param response - структура ответа
     */
    public WeatherInfo( Response response) {
        this.city = response.getName();
        this.shortDescription = response.getWeather()[0].getMain();
        this.description = response.getWeather()[0].getDescription();
        this.temperature = response.getMain().getTemp();
        this.feelsLikeTemperature = response.getMain().getFeelsLike();
        this.windSpeed = response.getWind().getSpeed();
        this.pressure = response.getMain().getPressure();
        this.expiryTime = LocalDateTime.now().plusMinutes(FIVE_MINUTES);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "WeatherInfo\n{" +
                " city='" + city + '\'' +
                ", \n shortDescription='" + shortDescription + '\'' +
                ", \n description='" + description + '\'' +
                ", \n temperature=" + temperature +
                ", \n feelsLikeTemperature=" + feelsLikeTemperature +
                ", \n windSpeed=" + windSpeed +
                ", \n pressure=" + pressure +
                ", \n expiryTime=" + expiryTime +
                " } \n";
    }
}
