package ru.sberbank.edu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Convert file to string list
 */
public class FileToList {
    List<String> list = new ArrayList<>();

    public void convFile(File file) throws IOException {
        String line;
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        while (bufferedReader.ready()) {
            line = bufferedReader.readLine();
            list.add(line);
        }
    }
}
