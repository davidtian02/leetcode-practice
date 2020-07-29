package com.leetcode.problems.easy.shortest_unsorted_continuous_subarray;

import java.util.Arrays;

// https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] sorted = new int[nums.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);

        int firstViolation = -1, lastViolation = -1;

        for (int i=0; i<nums.length; i++) {
            if (nums[i] != sorted[i]) {
                firstViolation = i;
                break;
            }
        }

        for (int i=nums.length-1; i>=0; i--) {
            if (nums[i] != sorted[i]) {
                lastViolation = i+1; // accomodates for len
                break;
            }
        }

        return lastViolation - firstViolation; // if none, still returns 0
    }
}
