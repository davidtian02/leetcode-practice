package com.leetcode.problems.medium.evaluate_reverse_polish_notation;

import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<>();
        int operand1, operand2;
        for (String token : tokens) {
            if (isOperation(token)) {
                operand2 = operands.pop();
                operand1 = operands.pop();
                operands.push(applyOperation(token, operand1, operand2));
            } else {
                operands.push(parseOperand(token));
            }
        }
        return operands.pop();
    }

    private boolean isOperation(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private Operation parseOperation(String token) {
        switch (token) {
            case "+":
                return Operation.ADD;
            case "-":
                return Operation.SUBTRACT;
            case "*":
                return Operation.MULTIPLY;
            case "/":
                return Operation.DIVIDE;
            default:
                throw new IllegalArgumentException();
        }
    }

    private int applyOperation(String op, int a, int b) {
        switch(parseOperation(op)) {
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b; // int is okay
            default:
                throw new IllegalArgumentException();
        }
    }

    private int parseOperand(String token) {
        return Integer.parseInt(token);
    }

    enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}