package com.leetcode.problems.medium.longest_palindrome_by_concatenating_two_letter_words;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestPalindrome(String[] words) {
        int maxLen = 0;
        Map<String, Integer> frequency = new HashMap<>();
        for (String w : words) {
            String otherHalf = "" + w.charAt(1) + w.charAt(0);
            if (frequency.containsKey(otherHalf)) {
                int count = frequency.get(otherHalf);
                if (count == 1) {
                    frequency.remove(otherHalf);
                } else {
                    frequency.put(otherHalf, count-1);
                }
                maxLen += 4;
            } else {
                if (!frequency.containsKey(w)) {
                    frequency.put(w, 0);
                }
                frequency.put(w, frequency.get(w)+1);
            }
        }

        boolean containsDoubleChar = false;
        for (String s : frequency.keySet()) {
            if (s.charAt(0) == s.charAt(1)) {
                containsDoubleChar = true;
                break;
            }
        }

        return containsDoubleChar ? maxLen + 2 : maxLen;
    }
}
