package com.leetcode.challenge.august2020.minimum_number_of_steps_to_make_two_strings_anagram;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/submissions/
// TODO actually, just use one char array instead.
class Solution {
    public int minSteps(String s, String t) {
        // fill s and t maps
        Map<Character, Integer> sFreq = fillFrequency(s);
        Map<Character, Integer> tFreq = fillFrequency(t);
        // cross matching
        removeIntersections(sFreq, tFreq);
        // sum remaining count in s
        return sumValues(sFreq);
    }

    private Map<Character, Integer> fillFrequency(String str) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i=0; i<str.length(); i++) {
            Character c = str.charAt(i);
            if (result.containsKey(c)) {
                result.put(c, result.get(c) + 1);
            } else {
                result.put(c, 1);
            }
        }
        return result;
    }

    private void removeIntersections(Map<Character, Integer> a, Map<Character, Integer> b) {
        a.forEach((key, value) -> {
            if (b.containsKey(key)) {
                int bval = b.get(key);
                if (bval >= value) {
                    a.put(key, 0);
                } else {
                    a.put(key, value - bval);
                }
            }
        });
    }

    private int sumValues(Map<Character, Integer> sFreq) {
        return sFreq.values().stream().mapToInt(v->v).sum();
    }
}