package com.leetcode.challenge.august2020.longest_palindrome;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/550/week-2-august-8th-august-14th/3423/
public class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c)+1);
            } else {
                freq.put(c, 1);
            }
        }

        boolean hasOdd = false;
        int result = 0;
        for (int count : freq.values()) {
            if ((count&0x01) == 1) {
                hasOdd = true;
                result += count - 1;
            } else {
                result += count;
            }
        }

        return hasOdd ? result+1 : result;
    }
}
