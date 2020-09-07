package com.leetcode.problems.medium.kth_largest_element_in_an_array;

import java.util.Arrays;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int[] sorted = Arrays.stream(nums).sorted().toArray();
        return sorted[sorted.length-k];
    }
}
