package com.assignment.privateinvestigator.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class PrintOutputToFile
{
    /**
        This method is used to print the grouped sentences along with the changing word to the output file
     */
    public static void printGroupedSentencesToFile(PrintWriter pw, List<String> sentences,
                                        List<List<String>> splittedSentences,
                                        Map<Integer, LinkedHashSet<Integer>> kthPositionVsGroup) {

        kthPositionVsGroup.forEach((k, v) -> {

            List<String> wordDiffs = new ArrayList<>();
            v.forEach(indexPos -> {
                pw.println(sentences.get(indexPos));
                wordDiffs.add(splittedSentences.get(indexPos).get(k));
            });

            pw.println("The changing word was: " + String.join(", ", wordDiffs));
        });
    }
}
