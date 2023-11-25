package ru.sberbank.edu;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Unit test for simple App.
 */
public class AppTest{
    @Test
    void getLineCount(){
        StatisticImpl statisticMoc = Mockito.spy(StatisticImpl.class);
        List<String> testList = Arrays.asList("Lllllllllllllongest", "Space Space Space", "Hello World");
        Assertions.assertEquals(statisticMoc.getLineCount(testList),3);
    }
    @Test
    void getSpaceCount(){
        StatisticImpl statisticMoc = Mockito.spy(StatisticImpl.class);
        List<String> testList = Arrays.asList("Lllllllllllllongest", "Space Space Space", "Hello World");
        Assertions.assertEquals(statisticMoc.getSpaceCount(testList),3);
    }

    @Test
    void getLongestLine(){
        StatisticImpl statisticMoc = Mockito.spy(StatisticImpl.class);
        List<String> testList = Arrays.asList("Lllllllllllllongest", "Space Space Space", "Hello World");
        Assertions.assertEquals(statisticMoc.getLongestLine(testList),"Lllllllllllllongest");
    }
}
