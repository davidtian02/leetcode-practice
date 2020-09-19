package com.leetcode.problems.easy.range_sum_query_immutable;

class NumArray {

    int[] nums;
    int[] dp;
    public NumArray(int[] nums) {
        this.nums = nums;

        if (nums.length <= 0) {
            return;
        }
        dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i=1; i<nums.length; i++) {
            dp[i] = dp[i-1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        // assert i <= j;
        if (nums.length <=0) {
            return 0;
        }
        return dp[j] - dp[i] + nums[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */