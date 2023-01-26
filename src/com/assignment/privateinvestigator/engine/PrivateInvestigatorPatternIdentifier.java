package com.assignment.privateinvestigator.engine;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrivateInvestigatorPatternIdentifier
{
    /**
        This method is used to find whether a pair of sentences differ only by one word
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
