package ru.sberbank.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class StatisticImpl implements Statistic {
    /***
     * Сollect statistics on a file
     */
    @Override
    public int getLineCount(List<String> list) {
        return list.size();
    }

    @Override
    public int getSpaceCount(List<String> list) {
        int countSpace = 0;
        for (String s : list) {
            countSpace += s.length() - s.replaceAll(" ", "").length();
        }
        return countSpace;
    }

    @Override
    public String getLongestLine(List<String> list) {
        return Collections.max(list, Comparator.comparing(s -> s.length()));
    }

    @Override
    public void save(int lineCount, int spaceCount, String line) {
        File file = new File("savedFile.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("number of rows: " + lineCount + "\n");
            writer.write("number of spaces: " + spaceCount + "\n");
            writer.write("the longest line: " + line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
