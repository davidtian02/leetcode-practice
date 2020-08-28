package com.leetcode.problems.easy.three_consecutive_odds;

// https://leetcode.com/problems/three-consecutive-odds/
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        int i=0;
        while (i<arr.length-2) {
            if ((arr[i] & 0x01) == 1) {
                i++;
                if ((arr[i] & 0x01) == 1) {
                    i++;
                    if ((arr[i] & 0x01) == 1) {
                        return true;
                    }
                }
            }
            i++;
        }

        return false;
    }
}
