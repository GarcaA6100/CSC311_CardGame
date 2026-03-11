package com.example.csc311_cardgame;

import java.util.ArrayList;
import java.util.List;

public class ExpressionEvaluator {

    private String expression;
    private int pos;

    public double evaluate(String expr) {
        this.expression = expr.replaceAll("\\s+", "");
        this.pos = 0;
        double result = parseExpression();
        if (pos != expression.length()) {
            throw new IllegalArgumentException("Invalid expression: unexpected character at position " + pos);
        }
        return result;
    }

    private double parseExpression() {
        double left = parseTerm();
        while (pos < expression.length()) {
            char op = expression.charAt(pos);
            if (op == '+' || op == '-') {
                pos++;
                double right = parseTerm();
                left = (op == '+') ? left + right : left - right;
            } else break;
        }
        return left;
    }

    private double parseTerm() {
        double left = parseFactor();
        while (pos < expression.length()) {
            char op = expression.charAt(pos);
            if (op == '*' || op == '/') {
                pos++;
                double right = parseFactor();
                if (op == '/') {
                    if (right == 0) throw new IllegalArgumentException("Division by zero.");
                    left = left / right;
                } else {
                    left = left * right;
                }
            } else break;
        }
        return left;
    }

    private double parseFactor() {
        if (pos >= expression.length())
            throw new IllegalArgumentException("Unexpected end of expression.");

        char ch = expression.charAt(pos);

        if (ch == '-') { pos++; return -parseFactor(); }

        if (ch == '(') {
            pos++;
            double result = parseExpression();
            if (pos >= expression.length() || expression.charAt(pos) != ')')
                throw new IllegalArgumentException("Missing closing parenthesis.");
            pos++;
            return result;
        }

        if (Character.isDigit(ch)) {
            int start = pos;
            while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) pos++;
            return Double.parseDouble(expression.substring(start, pos));
        }

        throw new IllegalArgumentException("Unexpected character: " + ch);
    }

    public static List<Integer> extractNumbers(String expr) {
        List<Integer> numbers = new ArrayList<>();
        String clean = expr.replaceAll("\\s+", "");
        int i = 0;
        while (i < clean.length()) {
            if (Character.isDigit(clean.charAt(i))) {
                int start = i;
                while (i < clean.length() && Character.isDigit(clean.charAt(i))) i++;
                numbers.add(Integer.parseInt(clean.substring(start, i)));
            } else i++;
        }
        return numbers;
    }
}