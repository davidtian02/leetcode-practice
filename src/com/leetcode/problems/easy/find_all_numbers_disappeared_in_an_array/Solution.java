package com.leetcode.problems.easy.find_all_numbers_disappeared_in_an_array;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/submissions/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // since the values of elements are all ints of the index+1, we will swap them to the correct order
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != i+1 && nums[i] != -1) {
                swapGame(nums, i);
            }
        }

        List<Integer> result = new LinkedList<>();
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == -1) {
                result.add(i+1);
            }
        }
        return result;
    }

    private void swapGame(int[] nums, int index) {
        // first mark out index, then start swapping it. stop when reached a "correct" value == index+1
        int temp = nums[index];
        nums[index] = -1;
        index = temp-1;
        while (index+1 != nums[index]) {
            temp = nums[index];
            nums[index] = index+1; // fixed it
            if (temp == -1) {
                return; // cuz done swapping
            }
            index = temp-1;
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }
}
