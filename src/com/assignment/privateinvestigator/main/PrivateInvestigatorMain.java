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
        System.out.println("Starting the grouping process of sentences...!!!");
        PrivateInvestigatorPatternIdentifier.performGrouping();
        System.out.println("Successfully completed the grouping process of sentences...!!!");
    }
}

