package com.assignment.privateinvestigator.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReadInputFileAndFill {

    /**
     * This method is used to read the input file and fill the data structures
     */
    public static void readFileAndFill(String filePath,
                                       Map<Integer, List<String>> sizeVsSentences, Map<Integer,
            List<List<String>>> sizeVsSplittedSentences) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;

                String[] words = line.split(" ");
                sizeVsSplittedSentences.computeIfAbsent(words.length, k -> new ArrayList<>())
                        .add(Arrays.asList(words));
                sizeVsSentences.computeIfAbsent(words.length, k -> new ArrayList<>())
                        .add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
