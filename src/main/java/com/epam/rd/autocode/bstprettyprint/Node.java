package com.epam.rd.autocode.bstprettyprint;

import java.util.Collections;
import java.util.List;

public class Node {
    private final Integer element;
    private final List<Integer> elements;
    private Node left, right;
    private final String turnUpDown = "┤";
    private final String verticalLine = "│";
    private final String turnLeftUp = "┌";
    private final String turnLeftDown = "└";
    private final String turnRightUp = "┐";
    private final String turnRightDown = "┘";
    private final String space = " ";

    Node(int value) {
        this.elements = Collections.singletonList(value);
        this.element = value;
        this.right = null;
        this.left = null;
    }

    public Integer getElement() {
        return element;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    private String printNodeValue() {
        StringBuilder stringBuilder = new StringBuilder();
        if (right != null && left != null)
            stringBuilder.append(element.toString()).append(turnUpDown);
        if (right != null && left == null)
            stringBuilder.append(element.toString()).append(turnRightDown);
        if (right == null && left != null)
            stringBuilder.append(element.toString()).append(turnRightUp);
        if (right == null && left == null)
            stringBuilder.append(element.toString());
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }

    private String printTree(boolean isRight, String indent) {
        StringBuilder stringBuilder = new StringBuilder();
        String spaces = addDigitSpaces(element);
        if (right != null)
            stringBuilder.append(right.printTree(true, indent + spaces + (isRight ? space : verticalLine)));
        stringBuilder.append(indent);

        if (isRight) {
            stringBuilder.append(spaces);
            stringBuilder.append(turnLeftUp);
        } else {
            stringBuilder.append(spaces);
            stringBuilder.append(turnLeftDown);
        }
        stringBuilder.append(printNodeValue());
        if (left != null)
            stringBuilder.append(left.printTree(false, indent + spaces + (isRight ? verticalLine : space)));
        return stringBuilder.toString();
    }

    private String addDigitSpaces(Integer integer) {
        return " ".repeat(String.valueOf(integer).length());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (right != null)
            stringBuilder.append(right.printTree(true, ""));
        stringBuilder.append(printNodeValue());
        if (left != null)
            stringBuilder.append(left.printTree(false, ""));
        return stringBuilder.toString();
    }
}