package com.leetcode.challenge.august2020.sort_array_by_parity;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/551/week-3-august-15th-august-21st/3431/
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while (start < end) {
            if ((A[start] & 0x01) == 1) {
                while (end > start) {
                    if ((A[end] & 0x01) == 0) {
                        int temp = A[end];
                        A[end] = A[start];
                        A[start] = temp;
                        break;
                    }
                    end--;
                }
            }

            start++;
        }
        return A;
    }
}
