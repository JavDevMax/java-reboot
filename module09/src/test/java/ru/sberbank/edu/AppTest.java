package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void getPositiveTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.sberbank.edu");
        WeatherProvider weatherProvider = context.getBean(WeatherProvider.class);
        WeatherInfo weatherInfo = weatherProvider.get("MOSKOW");
        Assertions.assertNotNull(weatherInfo);
    }

    @Test
    public void getNegativeTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.sberbank.edu");
        WeatherProvider weatherProvider = context.getBean(WeatherProvider.class);
        WeatherInfo weatherInfo = weatherProvider.get("qwerty");
        Assertions.assertNull(weatherInfo);
    }
}
