package com.leetcode.problems.medium.container_with_most_water;

// https://leetcode.com/problems/container-with-most-water/
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int start = 0, end = height.length-1;
        int maxHeightLeft=height[start], maxHeightRight=height[end];

        while (start < end) {
            int area = (end-start) * Math.min(height[start], height[end]);
            max = Math.max(max, area);
            if (maxHeightLeft < maxHeightRight) {
                start++;
                maxHeightLeft = Math.max(maxHeightLeft, height[start]);
            } else {
                end--;
                maxHeightRight = Math.max(maxHeightRight, height[end]);
            }
        }

        return max;
    }
}
