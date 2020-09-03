package com.leetcode.challenge.september2020.repeated_substring_pattern;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3447/
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // what about like aabaaaabaa and babbab ?
        for (int i=0; i<s.length()>>1; i++) {
            String sub = s.substring(0,i+1);
            if (checksSubstring(s, sub)) {
                return true;
            }
        }
        return false;
    }
    private boolean checksSubstring(String whole, String part) {
        int w = whole.length();
        int p = part.length();
        if (w % p != 0) {
            return false;
        }
        for (int i = 0; i+ p <whole.length(); i+= p) {
            for (int j = i; j<i+ p; j++) {
                if (whole.charAt(j) != whole.charAt(j+ p)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.repeatedSubstringPattern("aba"));
        System.out.println(runner.repeatedSubstringPattern("a"));
    }
}
