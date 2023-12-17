package ru.sberbank.edu;

import java.util.Comparator;

/**
 * Сортируем данные на четные и нечетные
 * Сначаал идут четные, затем нечетные
 *  0 - равны
 * -1 - четное первое
 *  1 - остальное
 */
public class CustomDigitComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {

        if (o1 == null || o2 == null) {
            throw new RuntimeException(); }

            int first = o1 % 2;
            int second = o2 % 2;

        if (first == second) {
            return 0;
        } else {
            if (first != 0) {
                return 1;
            } else {
                return -1;
            }
        }


        }
    }