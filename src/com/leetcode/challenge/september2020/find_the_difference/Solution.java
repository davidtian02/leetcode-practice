package com.leetcode.challenge.september2020.find_the_difference;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/557/week-4-september-22nd-september-28th/3471/
class Solution {
    public char findTheDifference(String s, String t) {
        int teller = 0; // maybe can xor?
        for (int i=0,len=s.length(); i<len; i++) {
            teller ^= s.charAt(i)-'a';
        }
        for (int i=0,len=t.length(); i<len; i++) {
            teller ^= t.charAt(i)-'a';
        }
        return (char) (teller+'a');
    }
}
