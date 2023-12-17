package ru.sberbank.edu;

import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    @Test
    void main() {


        List<Integer> intList = new ArrayList<>();

        intList.add(2);
        intList.add(3);
        intList.add(5);

        Comparator<Integer> comporator = new CustomDigitComparator();
        intList.sort(comporator);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Максим", "Москва", 28));
        personList.add(new Person("Паша", "Белгород", 22));

        personList.sort(Person::compareTo);

        Assertions.assertThat(intList.get(0) == 2 &&
                                     intList.get(1) == 3 &&
                                     intList.get(2) == 5 ).isTrue();
        Assertions.assertThat(personList.get(1).getCity().equals("Москва") &&
                                     personList.get(1).getName().equals("Максим") &&
                                     personList.get(0).getCity().equals("Белгород") &&
                                     personList.get(0).getName().equals("Паша")).isTrue();
    }
}