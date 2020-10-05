package com.leetcode.problems.hard.first_missing_positive;

// https://leetcode.com/problems/first-missing-positive/
public class Solution {
    // Runtime, space: O(n) O(1)
    public int firstMissingPositive(int[] nums) {
        // mark out all negative and out of range to be 0
        // if have biggest, store that
        // if in place, keep moving. else, swap

        if (nums.length < 1) {
            return 1;
        }

        for (int i=0; i<nums.length; i++) {
            if (nums[i] < 0 || nums[i] > nums.length) {
                nums[i] = 0;
            }
        }

        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0 && nums[i] != i+1) {
                swapAround(nums, i);
            }
        }

        for (int i=0; i<nums.length; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }

        return nums.length + 1;
    }

    private void swapAround(int nums[], int index) {
        while (nums[index] != index+1) {
            if (nums[index] == 0) {
                break;
            }

            int temp = nums[index];
            int other = nums[temp-1];
            if (temp == other) break;

            nums[index] = nums[temp-1];
            nums[temp-1] = temp;
        }
    }
}
