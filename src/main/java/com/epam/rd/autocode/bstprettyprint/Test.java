package com.epam.rd.autocode.bstprettyprint;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Test {
    private static final List<String> elements = Arrays.asList("184, 306, 973, 742, 640, 349, 630, 910, 836, 10, 968, 516, 115".split(", "));
    private static final String node = "┤";
    private static final String verticalLine = "│";
    private static final String turnLeftUp = "┌";
    private static final String turnLeftDown = "└";
    private static final String turnRightUp = "┐";
    private static final String turnRightDown = "┘";

    public static void main(String[] args) {
        String s =
                "   ┌10┐\n" +
                        "   │  └115\n" +
                        "184┤\n" +
                        "   └306┐\n" +
                        "       │           ┌349┐\n" +
                        "       │           │   │   ┌516\n" +
                        "       │           │   └630┘\n" +
                        "       │       ┌640┘\n" +
                        "       │   ┌742┤\n" +
                        "       │   │   │   ┌836\n" +
                        "       │   │   └910┤\n" +
                        "       │   │       └968\n" +
                        "       └973┘\n";


        HashSet<Integer> treeSet = new HashSet<>();
        for (String element : elements) {
            treeSet.add(Integer.valueOf(element));
        }
        System.out.println(treeSet);

        HashMap<Integer, Pair<String, String>> pairElements = new HashMap<>();
        Pair<String, String> pair = new ImmutablePair<>("l", "Z");
        pairElements.put(1, pair);
        System.out.println(pairElements.get(1).toString());
    }
}