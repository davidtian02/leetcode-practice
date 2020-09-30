package com.leetcode.challenge.september2020.first_missing_positive;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/558/week-5-september-29th-september-30th/3478/
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length < 1) return 1;
        boolean hasLength = false;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == nums.length) {
                hasLength = true; // because this will get swapped out?
            }
            if (nums[i] != i) {
                swapLoop(nums, i);
            }
        }

        for (int i=1; i<nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        if (nums[0] == nums.length) { // missing piece
            hasLength = true;
        }

        return hasLength ? nums.length+1 : nums.length;
    }

    private void swapLoop(int[] nums, int index) {
        int val = nums[index];
        while (val != index) {
            if (outOfBounds(nums, val)) {
                return; // don't need to do much here, since this val doesn't exist yet
            } else { // now it's between 1 and nums.length
                int swapToVal = nums[val];
                    if (swapToVal == val) {
                        return; // already in place
                    } else { // we assume it's out of place, so then we swap
                        nums[index] = swapToVal;
                        nums[val] = val;
                    }
            }
            val = nums[index];
        }
    }

    private boolean outOfBounds(int[] nums, int val) {
        return val < 0 || val >= nums.length;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.firstMissingPositive(new int[]{1,2,3})); // 4
        System.out.println(runner.firstMissingPositive(new int[]{0,1,2})); // 3
        System.out.println(runner.firstMissingPositive(new int[]{1,1})); // 2
        System.out.println(runner.firstMissingPositive(new int[]{0})); // 1
        System.out.println(runner.firstMissingPositive(new int[]{})); // 1
        System.out.println(runner.firstMissingPositive(new int[]{1})); // 2
        System.out.println(runner.firstMissingPositive(new int[]{2})); // 1
        System.out.println(runner.firstMissingPositive(new int[]{3,4,-1,1})); // 2
        System.out.println(runner.firstMissingPositive(new int[]{1,2,0})); // 3
        System.out.println(runner.firstMissingPositive(new int[]{7,8,9,11,12})); // 1
    }
}
