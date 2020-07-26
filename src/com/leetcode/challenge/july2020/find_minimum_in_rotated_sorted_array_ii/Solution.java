package com.leetcode.challenge.july2020.find_minimum_in_rotated_sorted_array_ii;

// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/547/week-4-july-22nd-july-28th/3401/
public class Solution {
    public int findMin(int[] nums) {
        int min = nums[0];
        for (int n : nums) {
            if (n < min) {
                return n;
            }
        }
        return min;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.findMin(new int[]{1, 3, 5}));
        System.out.println(runner.findMin(new int[]{2, 2, 2, 0, 1}));
    }
}
