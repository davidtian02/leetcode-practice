package com.leetcode.problems.medium.sort_colors;

import java.util.Arrays;

class Solution {
    public void sortColors(int[] nums) {
        final int[] freq = new int[3]; // constant space
        Arrays.stream(nums).forEach(a->freq[a]++);
        Arrays.fill(nums, 0, freq[0], 0);
        Arrays.fill(nums, freq[0], freq[0]+freq[1], 1);
        Arrays.fill(nums, nums.length-freq[2], nums.length, 2);
    }
}
