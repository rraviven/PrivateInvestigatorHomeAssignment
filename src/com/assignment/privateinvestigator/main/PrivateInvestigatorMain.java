package com.assignment.privateinvestigator.main;

import com.assignment.privateinvestigator.engine.PrivateInvestigatorPatternIdentifier;
import com.assignment.privateinvestigator.util.PrintOutputToFile;
import com.assignment.privateinvestigator.util.ReadInputFileAndFill;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class PrivateInvestigatorMain {
	public static void main(String args[]) {
		
		String inputFilePath = "src/resources/input/";
		String outFilePath = "src/resources/output/";
		
		/**
		 Grouping strings according to no. of words in sentences.
		 No Of Words In Sentence vs corresponding list of sentences
		 */
		Map<Integer, List<List<String>>> sizeVsSplittedSentences = new HashMap<>();
		Map<Integer, List<String>> sizeVsSentences = new HashMap<>();
		ReadInputFileAndFill.readFileAndFill(inputFilePath + "input.txt", sizeVsSentences, sizeVsSplittedSentences);
		
		try (PrintWriter pw = new PrintWriter(outFilePath + "output.txt")){
			
			for(Map.Entry<Integer, List<List<String>>> entry : sizeVsSplittedSentences.entrySet()) {
				
				System.out.println("Processing sentences with word count = " 
						+ Integer.valueOf(entry.getKey() - 2) + ", no. of sentences = " + entry.getValue().size()); // -2 for excluding Date and time

				List<List<String>> splittedSentences = entry.getValue();
				Map<Integer, LinkedHashSet<Integer>> globalKthPositionVsGroup = new HashMap<>();
				
				for(int firstSentPos = 0; firstSentPos < splittedSentences.size()-1; firstSentPos++) {

					Map<Integer, LinkedHashSet<Integer>> kthPositionVsGroup = new HashMap<>();
					for(int secSentPos = firstSentPos + 1; secSentPos < splittedSentences.size(); secSentPos++) {
						PrivateInvestigatorPatternIdentifier.findAndAddIfPairOfSentencesDifferingByOneWord(firstSentPos, secSentPos, splittedSentences, kthPositionVsGroup, globalKthPositionVsGroup);
					}

					PrintOutputToFile.printGroupedSentencesToFile(pw, sizeVsSentences.get(entry.getKey()), entry.getValue(), kthPositionVsGroup);
				}
			}
			System.out.println("Processing completed and written output to the file...!!!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}

}

