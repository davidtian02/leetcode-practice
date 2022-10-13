package com.leetcode.problems.medium.shuffle_an_array;

class Solution {

    private int[] original;
    private int[] nums;

    public Solution(int[] nums) {
        original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
        this.nums = nums;
    }

    public int[] reset() {
        return original;
    }

    public int[] shuffle() {
        int range = nums.length;
        for (int i=0; i<nums.length; i++) {
            int a = (int) (Math.random()*range);
            int b = (int) (Math.random()*range);
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
        return nums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
