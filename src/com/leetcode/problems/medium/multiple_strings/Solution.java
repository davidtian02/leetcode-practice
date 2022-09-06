package com.leetcode.problems.medium.multiple_strings;

import java.util.Stack;

class Solution {
    public String multiply(String num1, String num2) {
        int offset = 0;
        String shorter = num1.length() <= num2.length()? num1 : num2;
        String longer = num1.length() > num2.length()? num1 : num2;
        String product = null;

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        for (int i=shorter.length()-1; i>=0; i--) {
            int fixedDigit = Character.getNumericValue(shorter.charAt(i));
            String temp = digitMultiply(longer, fixedDigit, offset);
            product = add(product, temp);
            offset++;
        }
        return product;
    }

    private String digitMultiply(String longer, int fixedDigit, int offset) {
        Stack<Integer> stack = new Stack<>();
        int carry = 0;
        for (int i=longer.length()-1; i>=0; i--) {
            int tp = Character.getNumericValue(longer.charAt(i)) * fixedDigit;
            tp += carry;
            carry = tp / 10;
            int d = tp % 10;
            stack.push(d);
        }

        if (carry > 0) {
            stack.push(carry);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        for (int i=0; i<offset; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    private String add(String product, String temp) {
        if (product == null) {
            return temp;
        }
        String l = product.length() > temp.length()? product : temp;
        String s = product.length() <= temp.length()? product : temp;
        StringBuilder sum = new StringBuilder();
        int carry = 0;
        StringBuilder longer = new StringBuilder(l).reverse();
        StringBuilder shorter = new StringBuilder(s).reverse();
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<longer.length(); i++) {
            int d1 = Character.getNumericValue(longer.charAt(i));
            int d2 = (i>=shorter.length())? 0 : Character.getNumericValue(shorter.charAt(i));
            int t = d1 + d2 + carry;
            carry = t / 10;
            int d = t % 10;
            stack.push(d);
        }


        if (carry > 0) {
            sum.append(carry);
        }

        while (!stack.isEmpty()) {
            sum.append(stack.pop());
        }

        return sum.toString();
    }

}