package com.leetcode.challenge.october2020.repeated_dna_sequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/561/week-3-october-15th-october-21st/3498/
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        for (int i=0; i<=s.length()-10; i++) {
            String sub = s.substring(i, i+10);
            if (seen.contains(sub)) {
                repeated.add(sub);
            } else {
                seen.add(sub);
            }
        }

        return new ArrayList<>(repeated);
    }
}