package com.leetcode.problems.hard.trapping_rain_water;

import java.util.Arrays;

// https://leetcode.com/problems/trapping-rain-water/
class Solution {
    // we start with max height. then go left and right, finding next max. fill everything in between
    public int trap(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int maxHeight=-1, maxHeightIndex=-1;
        for (int i=0; i<height.length; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
                maxHeightIndex = i;
            }
        } // TODO can reuse

        int[] dp = new int[height.length];
        fillLeft(height, maxHeightIndex, dp);
        fillRight(height, maxHeightIndex, dp);
        return Arrays.stream(dp).sum();
    }

    private void fillLeft(int[] heights, int startIndex, int[] dp) {
        int midMax = heights[startIndex];
        int min = Integer.MAX_VALUE;
        boolean hasAnotherDip = false;
        int maxHeightToLeft = -1, maxHeightToLeftIndex = -1;
        for (int i=startIndex-1; i>=0; i--) {
            min = Math.min(min, heights[i]);
            if (heights[i] > min) {
                hasAnotherDip = true;
            }
            if (heights[i] >= maxHeightToLeft) {
                maxHeightToLeft = heights[i];
                maxHeightToLeftIndex = i;
            }
        }

        if (!hasAnotherDip) {
            return; // linear
        }
        // then backfill elements
        for (int i=maxHeightToLeftIndex; i<startIndex; i++) {
            dp[i] = maxHeightToLeft - heights[i];
        }

        fillLeft(heights, maxHeightToLeftIndex, dp);
    }

    private void fillRight(int[] heights, int startIndex, int[] dp) {
        int midMax = heights[startIndex];
        int min = Integer.MAX_VALUE;
        boolean hasAnotherDip = false;
        int maxHeightToRight = -1, maxHeightToRightIndex = -1;
        for (int i=startIndex+1; i<heights.length; i++) {
            min = Math.min(min, heights[i]);
            if (heights[i] > min) {
                hasAnotherDip = true;
            }
            if (heights[i] >= maxHeightToRight) {
                maxHeightToRight = heights[i];
                maxHeightToRightIndex = i;
            }
        }

        if (!hasAnotherDip) {
            return; // linear
        }
        // then backfill elements
        for (int i=maxHeightToRightIndex; i>startIndex; i--) {
            dp[i] = maxHeightToRight - heights[i];
        }

        fillRight(heights, maxHeightToRightIndex, dp);
    }
}
