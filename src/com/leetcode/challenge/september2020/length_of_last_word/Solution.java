package com.leetcode.challenge.september2020.length_of_last_word;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/556/week-3-september-15th-september-21st/3461/
class Solution {
    public int lengthOfLastWord(String s) {
        int lastCharLen = s.length();
        for (int i=lastCharLen-1; i>=0; i--) {
            if (s.charAt(i) != ' ') {
                break;
            }
            lastCharLen--;
        }

        int len = 0;
        for (int i=lastCharLen-1; i>=0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
            len++;
        }
        return len;
    }
}
