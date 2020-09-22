package com.leetcode.problems.medium.find_all_anagrams_in_a_string;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
class Solution {
    private static final char OFFSET = 'a';

    // note: if you wanted to optimize speed, can use for-loop. much quicker
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        int pLen = p.length(), sLen = s.length();
        if (pLen == 0 || sLen == 0 || pLen > sLen) {
            return result;
        }
        // build freq array
        int[] freq = new int[26];
        // init
        for (int i=0; i<pLen; i++) {
            freq[p.charAt(i) - OFFSET]++;
            freq[s.charAt(i) - OFFSET]--;
        }

        // each time, add current and remove first. check the 26-size freq to see if all 0. if so, anagram
        if (Arrays.stream(freq).noneMatch(a1 -> a1 != 0)) result.add(0);

        for (int tail=pLen; tail<sLen; tail++) {
            freq[s.charAt(tail- pLen)-OFFSET]++; // release head
            freq[s.charAt(tail)-OFFSET]--; // lock current
            if (Arrays.stream(freq).noneMatch(a -> a != 0)) result.add(1+tail- pLen);
        }

        return result;
    }

}

