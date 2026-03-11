package com.example.csc311_cardgame;

import java.util.ArrayList;
import java.util.List;

public class SolutionFinder {

    private static final char[] OPERATORS = {'+', '-', '*', '/'};
    private static final double TARGET = 24.0;
    private static final double EPSILON = 1e-9;

    public String findSolution(List<Card> cards) {
        int[] values = {
                cards.get(0).getValue(), cards.get(1).getValue(),
                cards.get(2).getValue(), cards.get(3).getValue()
        };
        List<int[]> permutations = new ArrayList<>();
        generatePermutations(new int[]{0,1,2,3}, 0, permutations);

        for (int[] perm : permutations) {
            int a = values[perm[0]], b = values[perm[1]];
            int c = values[perm[2]], d = values[perm[3]];
            String result = tryAllOperators(a, b, c, d);
            if (result != null) return result;
        }
        return null;
    }

    private String tryAllOperators(int a, int b, int c, int d) {
        ExpressionEvaluator eval = new ExpressionEvaluator();
        for (char op1 : OPERATORS) {
            for (char op2 : OPERATORS) {
                for (char op3 : OPERATORS) {
                    String[] exprs = {
                            String.format("((%d %c %d) %c %d) %c %d", a,op1,b,op2,c,op3,d),
                            String.format("(%d %c (%d %c %d)) %c %d", a,op1,b,op2,c,op3,d),
                            String.format("(%d %c %d) %c (%d %c %d)", a,op1,b,op2,c,op3,d),
                            String.format("%d %c ((%d %c %d) %c %d)", a,op1,b,op2,c,op3,d),
                            String.format("%d %c (%d %c (%d %c %d))", a,op1,b,op2,c,op3,d)
                    };
                    for (String e : exprs) {
                        try {
                            if (Math.abs(eval.evaluate(e) - TARGET) < EPSILON) return e;
                        } catch (Exception ignored) {}
                    }
                }
            }
        }
        return null;
    }

    private void generatePermutations(int[] arr, int start, List<int[]> result) {
        if (start == arr.length) { result.add(arr.clone()); return; }
        for (int i = start; i < arr.length; i++) {
            int tmp = arr[start]; arr[start] = arr[i]; arr[i] = tmp;
            generatePermutations(arr, start + 1, result);
            tmp = arr[start]; arr[start] = arr[i]; arr[i] = tmp;
        }
    }
}