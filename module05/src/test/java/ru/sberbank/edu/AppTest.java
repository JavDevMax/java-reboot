package ru.sberbank.edu;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void whenReturnCitiesNamesList() {
        TravelService travelService = new TravelService();
        travelService.add(new CityInfo("Moscow", new GeoPosition("55(44'24'')", "37(36'36'')")));

        List<String> list = new ArrayList<>();
        list.add("Moscow");

        Assertions.assertEquals(list, travelService.citiesNames());
    }

    @Test
    public void getDistanceTest() {
        TravelService service = new TravelService();
        service.add(new CityInfo("Ekaterinburg", new GeoPosition("56(50'00'')", "60(35'00'')")));
        service.add(new CityInfo("Omsk", new GeoPosition("54(58'00'')", "73(23'00'')")));
        service.add(new CityInfo("Moscow", new GeoPosition("55(44'24'')", "37(36'36'')")));
        service.add(new CityInfo("Sochi", new GeoPosition("43(35'07'')", "39(43'13'')")));
        service.add(new CityInfo("Perm", new GeoPosition("55(09'00'')", "61(24'00'')")));

        Assertions.assertEquals(823, service.getDistance("Ekaterinburg", "Omsk"));

        try {
            service.getDistance("Chelyabinsk", "Moscow");
            Assertions.fail();
        } catch (IllegalArgumentException e) {}

        try {
            service.getDistance("Moscow", "London");
            Assertions.fail();
        } catch (IllegalArgumentException e) {}
    }
}
