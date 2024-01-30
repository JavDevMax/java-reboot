package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void getPositiveTest() {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherInfo weatherInfo = weatherProvider.get("Belgorod");
        Assertions.assertNotNull(weatherInfo);
    }

    @Test
    public void getNegativeTest() {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherInfo weatherInfo = weatherProvider.get("eufiwf");
        Assertions.assertNull(weatherInfo);
    }
}
