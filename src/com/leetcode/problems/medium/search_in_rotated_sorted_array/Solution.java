package com.leetcode.problems.medium.search_in_rotated_sorted_array;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length < 2) {
            return nums[0] == target ? 0 : -1;
        }
        // find max and min of nums
        int indexOfMax = findIndexOfMax(nums);
        int indexOfMin = indexOfMax == nums.length-1 ? 0 : indexOfMax+1;
        int min, max;
        min = nums[indexOfMin];
        max = nums[indexOfMax];


        if (target < min || target > max) { // then find in second array
            return -1;
        } else {
            // target could belong to left array or right array
            if (target >= nums[0]) {
                return findInArray(nums, target, 0, indexOfMax);
            } else {
                return findInArray(nums, target, indexOfMin, nums.length-1);
            }
        }
    }

    private int findIndexOfMax(int[] nums) {
        int anchor = 0;
        int step = 1;
        while (anchor+step < nums.length && nums[anchor] < nums[anchor+step]) {
            anchor += step;
            step <<= 1;
        }
        // now it's somewhere in between
        // TODO can make this better
        int i=anchor;
        while(i+1 < nums.length && nums[i+1] > nums[i]) {
            i++;
        }
        return i;
    }

    private int findInArray(int[] nums, int target, int start, int end) {
        if (start >= end) {
            return target == nums[end] ? end : -1;
        }
        int mid = ((end-start)>>1) + start;
        if (nums[mid] == target) {
            return mid;
        } else {
            return target < nums[mid] ? findInArray(nums, target, start, mid-1) : findInArray(nums, target, mid+1, end);
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.search(new int[]{1,3,5}, 0));
        System.out.println(runner.search(new int[]{1,3}, 0));
        System.out.println(runner.search(new int[]{4,5,6,7,8,1,2,3}, 8));
    }
}
