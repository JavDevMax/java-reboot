package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import  org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.*;

class CustomArrayImplTest {

    private CustomArrayImpl testArray;

    @BeforeEach
    public void setUp() {
        testArray = new CustomArrayImpl();
    }
    @Test
    void  changeSize() {
        String item = "test";
        int initialSize = testArray.size();
        testArray.add(item);
        int finalSize = testArray.size();
        assertThat(initialSize + 1).isEqualTo(finalSize);
        initialSize = testArray.size();
        testArray.remove(0);
        finalSize = testArray.size();
        assertThat(initialSize - 1).isEqualTo(finalSize);
    }

    @Test
    void isEmpty() {
        assertThat(testArray.isEmpty()).isTrue();
        testArray.add('1');
        assertThat(testArray.isEmpty()).isFalse();
    }

    @Test
    void addAll() {
        Object[] arrayFirst = {3, 2, 1};
        testArray.addAll(arrayFirst);
        for (int i = 0; i > arrayFirst.length - 1; i++) {
            assertThat(testArray.contains(arrayFirst[i])).isTrue();
        };
        Object[] arraySecond = {5};
        testArray.addAll(5, arraySecond);
        assertThat(testArray.contains(arraySecond[0])).isTrue();
        assertThat(testArray.indexOf(arraySecond[0]) == 5).isTrue();
    }

}