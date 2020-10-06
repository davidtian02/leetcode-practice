package com.leetcode.problems.medium.basic_calculator_ii;

import java.util.*;

// https://leetcode.com/problems/basic-calculator-ii/
class Solution {
    public int calculate(String s) {
        String s1 = s.replaceAll("\\s+", "");
        String[] operands = s1.split("[\\+\\-\\*\\/]");
        String[] operations = s1.split("[0-9]");
        int[] data = Arrays.stream(operands).mapToInt(Integer::parseInt).toArray();
        Object[] ops = Arrays.stream(operations).filter(a -> !a.isEmpty()).toArray();

        int prev = data[0];
        Deque<Integer> deqData = new ArrayDeque<>();
        deqData.addLast(prev);
        Queue<Character> deqOps = new LinkedList<>();
        for (int i=1; i<data.length; i++) {
            int j = i-1;
            char op = ((String)ops[j]).charAt(0);
            int d = data[i];
            if (op == '/' || op == '*') {
                prev = deqData.removeLast();
                if (op == '/') {
                    prev = prev / d;
                } else {
                    prev *= d;
                }
                deqData.addLast(prev);
            } else {
                deqData.addLast(d);
                deqOps.add(op);
            }
        }

        // 2nd pass, pemdas
        int result = deqData.removeFirst();
        while (!deqData.isEmpty()) {
            int d = deqData.removeFirst();
            char op = deqOps.remove();
            if (op == '+') {
                result += d;
            } else {
                result -= d;
            }
        }
        return result;
    }
}
