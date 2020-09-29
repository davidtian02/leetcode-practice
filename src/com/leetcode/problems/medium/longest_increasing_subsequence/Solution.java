package com.leetcode.problems.medium.longest_increasing_subsequence;

import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence/
class Solution {

    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            topDown(nums, i, dp);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    private int topDown(int[] nums, int index, int[] dp) {
        int max = 1; // at this level in the tree
        if (dp[index] != 0) {
            return dp[index];
        }

        for (int i=index; i<nums.length; i++) {
            if (nums[i] > nums[index]) {
                max = Math.max(1 + topDown(nums, i, dp), max);
            }
        }

        dp[index] = max;
        return max;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length < 1) {
            return 0;
        }
        dp[nums.length - 1] = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) { // base
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

}