package com.leetcode.challenge.august2020.valid_palindrome;

// https://leetcode.com/explore/featured/card/august-leetcoding-challenge/549/week-1-august-1st-august-7th/3411/
public class Solution {
    public boolean isPalindrome(String s) {
        String filtered = filterString(s);
        int len = filtered.length();
        int mid = len >> 1;
        for (int i=0; i<mid; i++) {
            if (filtered.charAt(i) != filtered.charAt((len-1) - i)) {
                return false;
            }
        }

        return true;
    }
    private String filterString(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}
