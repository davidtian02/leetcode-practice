package com.leetcode.challenge.september2020.word_pattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3451/
class Solution {
    // split str into words
    // each in pattern maps to a word
    public boolean wordPattern(String pattern, String str) {
        if (str.isEmpty()) {
            return pattern.isEmpty();
        }
        String[] words = str.split(" ");
        Map<Character, String> mapping = new HashMap<>();
        Set<String> dejaVu = new HashSet<>(); // we have to use a hashset because mapping.values().contains uses iterative lookup
        int len = pattern.length();
        if (words.length != len) {
            return false;
        }
        for (int i=0; i<len; i++) {
            char c = pattern.charAt(i);
            if (mapping.containsKey(c)) {
                String word = mapping.get(c);
                if (!word.equals(words[i])) {
                    return false;
                }
            } else {
                if (dejaVu.contains(words[i])) {
                    return false; // supposed to be new
                }
                mapping.put(c, words[i]);
                dejaVu.add(words[i]);
            }
        }
        return true;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.wordPattern("", ""));
        System.out.println(runner.wordPattern("a", "cat"));
        System.out.println(runner.wordPattern("abbc", "cat dog dog elephant"));
        System.out.println(runner.wordPattern("a", "cat tree"));
        System.out.println(runner.wordPattern("zz", "cat tree"));
    }
}
