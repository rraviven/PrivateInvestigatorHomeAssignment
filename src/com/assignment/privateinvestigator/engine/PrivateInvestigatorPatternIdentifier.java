package com.assignment.privateinvestigator.engine;

import com.assignment.privateinvestigator.util.PrintOutputToFile;
import com.assignment.privateinvestigator.util.ReadInputFileAndFill;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class PrivateInvestigatorPatternIdentifier {
    /**
     * This method performs the grouping logic by utilizing other helper methods
     */
    public static void performGrouping() {
        String inputFilePath = "src/resources/input/"; //Please change the file path if required
        String outFilePath = "src/resources/output/";

        /**
         Grouping strings according to no. of words in sentences
         No Of Words In Sentence vs corresponding list of sentences
         */
        Map<Integer, List<List<String>>> sizeVsSplittedSentences = new HashMap<>();
        Map<Integer, List<String>> sizeVsSentences = new HashMap<>();
        ReadInputFileAndFill.readFileAndFill(inputFilePath + "input.txt", sizeVsSentences, sizeVsSplittedSentences);

        try (PrintWriter pw = new PrintWriter(outFilePath + "output.txt")) {

            for (Map.Entry<Integer, List<List<String>>> entry : sizeVsSplittedSentences.entrySet()) {

                System.out.println("Processing sentences with word count = "
                        + (entry.getKey() - 2) + ", no. of sentences = " + entry.getValue().size()); // -2 for excluding Date and time

                List<List<String>> splittedSentences = entry.getValue();
                Map<Integer, LinkedHashSet<Integer>> globalKthPositionVsGroup = new HashMap<>();

                for (int firstSentPos = 0; firstSentPos < splittedSentences.size() - 1; firstSentPos++) {

                    Map<Integer, LinkedHashSet<Integer>> kthPositionVsGroup = new HashMap<>();
                    for (int secSentPos = firstSentPos + 1; secSentPos < splittedSentences.size(); secSentPos++) {
                        findAndAddIfPairOfSentencesDifferingByOneWord(firstSentPos, secSentPos, splittedSentences, kthPositionVsGroup, globalKthPositionVsGroup);
                    }

                    PrintOutputToFile.printGroupedSentencesToFile(pw, sizeVsSentences.get(entry.getKey()), entry.getValue(), kthPositionVsGroup);
                }
            }
            System.out.println("Processing completed and written output to the file...!!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is used to find whether a pair of sentences differ only by one word
     */
    public static boolean findAndAddIfPairOfSentencesDifferingByOneWord(int firstSentPos, int secSentPos,
                                                                        List<List<String>> splitSentences, Map<Integer, LinkedHashSet<Integer>> kthPositionVsGroup,
                                                                        Map<Integer, LinkedHashSet<Integer>> globalKthPositionVsGroup) {

        int wordPosition = -1;
        int wordDiffCount = 0;
        List<String> firstSentence = splitSentences.get(firstSentPos);
        List<String> secSentence = splitSentences.get(secSentPos);
        for (int wordPos = 2; wordPos < firstSentence.size(); wordPos++) {

            Set<Integer> set = globalKthPositionVsGroup.get(wordPos);
            if (set != null && set.contains(firstSentPos) && set.contains(secSentPos))
                break;

            if (!firstSentence.get(wordPos).equalsIgnoreCase(secSentence.get(wordPos))) {
                wordPosition = wordPos;
                wordDiffCount++;
                if (wordDiffCount > 1) {
                    break;
                }
            }
        }

        if (wordDiffCount == 1) {
            Set<Integer> set = kthPositionVsGroup.computeIfAbsent(wordPosition, key -> new LinkedHashSet<>());
            set.add(firstSentPos);
            set.add(secSentPos);
            set = globalKthPositionVsGroup.computeIfAbsent(wordPosition, key -> new LinkedHashSet<>());
            set.add(firstSentPos);
            set.add(secSentPos);
            return true;
        }

        return false;
    }
}
