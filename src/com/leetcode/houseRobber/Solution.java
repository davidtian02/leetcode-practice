package com.leetcode.houseRobber;

// 11:52 PM
// https://leetcode.com/submissions/detail/176351086/
// *** wow, that beat 100% of solutions. nice
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        mCache = new int[nums.length];
        for (int i=0; i<mCache.length; i++) {
            mCache[i] = -1;
        }
        return robHelper(nums, 0);
    }

    int[] mCache;
    private int robHelper(int[] nums, int start) {
        if (mCache[start] != -1) {
            return mCache[start];
        }

        int max;
        if (start >= nums.length - 1) {
            mCache[start] = nums[start];
            return nums[start];
        }
        if (start >= nums.length - 2) {
            max = Math.max(nums[start], nums[start+1]);
            mCache[start] = max;
            return max;
        }
        if (start >= nums.length - 3) {
            max = Math.max(nums[start] + nums[start+2], nums[start+1]);
            mCache[start] = max;
            return max;
        }
        if (start >= nums.length - 4) {
            int c1 = Math.max(nums[start] + nums[start+2], nums[start+1] + nums[start+3]);
            max = Math.max(c1, nums[start] + nums[start+3]);
            mCache[start] = max;
            return max;
        }

        max = Math.max(nums[start] + robHelper(nums, start+2), nums[start+1] + robHelper(nums, start+3));
        mCache[start] = max;
        return max;
    }
}