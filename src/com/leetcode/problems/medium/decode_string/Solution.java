package com.leetcode.problems.medium.decode_string;

import java.util.Stack;

// https://leetcode.com/problems/decode-string/
class Solution {
    public String decodeString(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder temp = new StringBuilder();
        for (int i=0,len=s.length(); i<len; i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(temp);
                temp = new StringBuilder();
            } else if (c == ']') {
                temp = evalBracketContents(temp, stack.pop());
            } else {
                temp.append(c);
            }
        }
        return temp.toString();
    }

    private StringBuilder evalBracketContents(StringBuilder temp, StringBuilder previous) {
        StringBuilder repeatedPattern = temp;
        StringBuilder prefixWithNum = previous;
        StringBuilder prefix = new StringBuilder();
        int j = 0;
        char p = prefixWithNum.charAt(j);
        while (Character.isLetter(p)) {
            prefix.append(p); // collects things like the "abc" in "abc2[a]"
            j++;
            p = prefixWithNum.charAt(j);
        }
        StringBuilder multiplier = new StringBuilder();
        while (Character.isDigit(p) && j < prefixWithNum.length()) {
            p = prefixWithNum.charAt(j);
            multiplier.append(p);
            j++;
        }
        int number = Integer.parseInt(multiplier.toString());
        StringBuilder repeated = new StringBuilder();
        for (int k=0; k<number; k++) { // adding suffix
            repeated.append(repeatedPattern);
        }
        temp = prefix.append(repeated);
        return temp;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.decodeString("abc"));
        System.out.println(runner.decodeString("2[a]"));
        System.out.println(runner.decodeString("0[]"));
        System.out.println(runner.decodeString("0[abc]"));
        System.out.println(runner.decodeString("1[]"));
        System.out.println(runner.decodeString("abc2[d]"));
        System.out.println(runner.decodeString("a2[zebra]b3[c]"));
        System.out.println(runner.decodeString("a3[bcd4[e]f]gh"));
    }
}

// abc
// 2[a]
// 0[]
// 0[abc]
// 1[]
// abc2[d]
// a2[zebra]b3[c]
// a1[bcd4[e]f]gh


