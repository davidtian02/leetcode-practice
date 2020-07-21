package com.leetcode.problems.medium.reverse_substrings_between_each_pair_of_parentheses;

import java.util.Stack;

// https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
public class Solution {

    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder prev;
        Stack<StringBuilder> words = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                words.push(sb);
                sb = new StringBuilder();
            } else if (c == ')') {
                sb = sb.reverse();
                prev = words.pop();
                sb = prev.append(sb);
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String... args) {
        System.out.println(reverseParentheses("n(ev(t)((()lfevf))yd)cb()"));
    }
}
