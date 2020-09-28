package com.leetcode.problems.easy.maximum_subarray;

class Solution {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int temp = sum;
        for (int i=1; i<nums.length; i++) {
            int next = nums[i];
            temp = Math.max(temp + next, next);
            sum = Math.max(temp, sum);
        }
        return sum;
    }
}
