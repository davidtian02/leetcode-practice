package com.leetcode.challenge.september2020.word_break;

import java.util.*;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/558/week-5-september-29th-september-30th/3477/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        cache = new HashMap<>();
        return canBreak(s, dict);
    }

    private Map<String, Boolean> cache;
    private boolean canBreak(String s, Set<String> dict) {
        if ("".equals(s) || dict.contains(s)) return true;
        if (cache.containsKey(s)) return cache.get(s);
        for (int i=1; i<s.length(); i++) {
            String sub = s.substring(0, i);
            if (dict.contains(sub)) {
                String rest = s.substring(i, s.length());
                boolean breakable = canBreak(rest, dict);
                if (breakable) {
                    cache.put(rest, true);
                    return true;
                }
            }
        }
        cache.put(s, false);
        return false;
    }
}