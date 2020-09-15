package com.leetcode.challenge.september2020.house_robber;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/555/week-2-september-8th-september-14th/3459/
class Solution {
    public int rob(int[] nums) {
        if (nums.length < 3) {
            switch(nums.length) {
                case 0: return 0;
                case 1: return nums[0];
                case 2: return Math.max(nums[0], nums[1]);
            }
        }
        int dpOdd = nums[0];
        int dpEven = Math.max(nums[0], nums[1]);
        int dpMax = 0;
        for (int i=2; i<nums.length; i++) {
            dpMax = Math.max(dpEven, dpOdd + nums[i]);
            dpOdd = dpEven;
            dpEven = dpMax;
        }
        return dpMax;
    }
}
