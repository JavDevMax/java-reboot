package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<Integer> intList = new ArrayList<>();

        intList.add(2);
        intList.add(3);
        intList.add(5);
        intList.add(7);
        intList.add(4);

        Comparator<Integer> comporator = new CustomDigitComparator();
        intList.sort(comporator);

        System.out.println(intList);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Максим", "Москва", 28));
        personList.add(new Person("Паша", "Белгород", 22));
        personList.add(new Person("Роман", "Город", 33));

        personList.sort(Person::compareTo);

        System.out.println(personList);
    }
}
