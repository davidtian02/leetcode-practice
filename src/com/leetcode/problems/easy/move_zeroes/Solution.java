package com.leetcode.problems.easy.move_zeroes;

// https://leetcode.com/problems/move-zeroes/
// TODO - should do this in O(n) time by simply over-writing all the array one at a time, until any remaining (if any), and then populating those with 0
class Solution {
    public void moveZeroes(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == 0) {
                // find first non-zero and swap with it
                int j=i+1;
                while (j < nums.length) {
                    if (nums[j] != 0) {
                        // swap
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                    j++;
                }
                if (j == nums.length) {
                    return; // cuz couldn't find any to swap with
                }
            }
        }
    }
}
