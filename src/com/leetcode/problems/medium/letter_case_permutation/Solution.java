package com.leetcode.problems.medium.letter_case_permutation;

import java.util.*;

// https://leetcode.com/problems/letter-case-permutation/
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> words = new LinkedList<>();
        permutate(words, S, 0, new StringBuilder());
        return words;
    }

    private void permutate(List<String> words, String s, int index, StringBuilder previous) {
        if (index == s.length()) {
            words.add(previous.toString());
            return;
        }

        char c = s.charAt(index);
        if (Character.isDigit(c)) {
            previous.append(c);
            permutate(words, s, index+1, previous);
        } else { // assume is character due to problem constraint, otherwise use Character.isLetter();
            StringBuilder copy = new StringBuilder(previous);
            permutate(words, s, index+1, previous.append(Character.toLowerCase(c)));
            permutate(words, s, index+1, copy.append(Character.toUpperCase(c)));
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.letterCasePermutation("a1b2"));
    }
}