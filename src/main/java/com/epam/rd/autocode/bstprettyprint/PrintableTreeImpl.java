package com.epam.rd.autocode.bstprettyprint;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrintableTreeImpl implements PrintableTree {
    private List<Integer> elements;
    private HashMap<Integer, Pair<Integer, Integer>> pairElements;
    private StringBuilder stringBuilder = new StringBuilder();

    private final String node = "┤";
    private final String verticalLine = "│";
    private final String turnLeftUp = "┌";
    private final String turnLeftDown = "└";
    private final String turnRightUp = "┐";
    private final String turnRightDown = "┘";

    public PrintableTreeImpl() {
        this.elements = new ArrayList<>();
        this.pairElements = new HashMap<>();
    }

    @Override
    public void add(int i) {
        elements.add(i);
    }

    @Override
    public String prettyPrint() {
        TreeSet<Integer> treeSet = new TreeSet<>(elements);
        countElementsAtRoots();
        return pairElements.toString();
    }

    private StringBuilder printElements(TreeSet<Integer> treeSet) {
        for (Integer largerElement : getLargerElements(treeSet, elements)) {
            stringBuilder
                    .append(addDigitSpaces(getRoot(elements)))

                    .append(largerElement)
                    .append("\n");
        }
        return stringBuilder;
    }

    private HashMap<Integer, Pair<Integer, Integer>> countElementsAtRoots() {
        for (Integer element : elements) {
            Integer smallerElements = getSmallerElementsInNaturalOrder(element).size();

            List<Integer> e = getSmallerElementsInNaturalOrder(element);
            System.out.println(e);

            Integer largerElements = getLargerElementsInNaturalOrder(element).size();
            Pair<Integer, Integer> counter = new ImmutablePair<>(smallerElements, largerElements);
            pairElements.put(element, counter);
        }
        return pairElements;
    }

    private List<Integer> getLargerElementsInNaturalOrder(Integer root) {
        return IntStream.range(elements.indexOf(root), elements.size())
                .filter(index -> elements.get(index) > root)
                .mapToObj(index -> elements.get(index))
                .collect(Collectors.toList());
    }

    private List<Integer> getSmallerElementsInNaturalOrder(Integer root) {
        return IntStream.range(elements.indexOf(root), elements.size() - elements.indexOf(root))
                .filter(index -> elements.get(index) < root)
                .mapToObj(index -> elements.get(index))
                .collect(Collectors.toList());
    }

    private NavigableSet<Integer> getLargerElements(TreeSet<Integer> treeSet, List<Integer> elements) {
        return treeSet.tailSet(getRoot(elements), false);
    }

    private NavigableSet<Integer> getSmallerElements(TreeSet<Integer> treeSet, List<Integer> elements) {
        return treeSet.headSet(getRoot(elements), false);
    }

    private void sequenceMethod() {
        Integer root = getRoot(elements);
        elements.sort(Comparator.naturalOrder());
        int counter = 0;

        for (int index = 0; index < elements.size(); index++) {
            if (index < root) {
                stringBuilder
                        .append(addDigitSpaces(root))
                        .append(turnLeftUp)
                        .append(elements.get(index))
                        .append("\n");
            }
            if (index == (root)) {
                stringBuilder.append(root).append(node).append("\n");
            }
            if (index > root && index != (elements.get(elements.size() - 1))) {
                stringBuilder
                        .append(addDigitSpaces(root))
                        .append(addSpaces(index - elements.indexOf(root) - 1 + counter))
                        .append(turnLeftDown)
                        .append(elements.get(index))
                        .append(turnRightUp)
                        .append("\n");
                counter++;
            }
            if (index > root && index == (elements.get(elements.size() - 1))) {
                stringBuilder
                        .append(addDigitSpaces(root))
                        .append(addSpaces(index - elements.indexOf(root) - 1 + counter))
                        .append(turnLeftDown)
                        .append(elements.get(index))
                        .append("\n");
            }
        }
    }

    private Integer getRoot(List<Integer> elements) {
        return elements.get(0);
    }

    private String addDigitSpaces(Integer integer) {
        return " ".repeat(String.valueOf(integer).length());
    }

    private String addSpaces(Integer value) {
        return " ".repeat(value);
    }
}