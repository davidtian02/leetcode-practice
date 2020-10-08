package com.leetcode.challenge.october2020.binary_search;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/560/week-2-october-8th-october-14th/3488/
class Solution {
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length-1, target);
    }

    private int search(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        } // or...
        int mid = ((right-left)>>1) + left;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            // go right
            return search(nums, mid+1, right, target);
        } else {
            // go left
            return search(nums, left, mid-1, target);
        }
    }
}
