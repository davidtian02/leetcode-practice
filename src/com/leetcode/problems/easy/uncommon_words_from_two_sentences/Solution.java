package com.leetcode.problems.easy.uncommon_words_from_two_sentences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/uncommon-words-from-two-sentences/
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Boolean> map = new HashMap<>();
        fillMap(A, map);
        fillMap(B, map);
        ArrayList<String> uniqs = new ArrayList<>();
        for (Map.Entry<String,Boolean> e : map.entrySet()) {
            if (!e.getValue()) { // unique
                uniqs.add(e.getKey());
            }
        }
        return uniqs.toArray(new String[0]);
    }

    private void fillMap(String sentence, Map<String, Boolean> map) {
        String[] tokens = sentence.split(" ");
        for (String t : tokens) {
            if (map.containsKey(t)) {
                map.put(t, true);
            } else {
                map.put(t, false); // unique
            }
        }
    }
}
