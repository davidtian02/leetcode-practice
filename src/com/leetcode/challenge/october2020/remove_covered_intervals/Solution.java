package com.leetcode.challenge.october2020.remove_covered_intervals;

import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int count = intervals.length;

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });
        int maxEnd = intervals[0][1];
        for (int i=1; i<intervals.length; i++) {
            int[] current = intervals[i];
            if (current[1] <= maxEnd) {
                count--;
            } else {
                maxEnd = current[1];
            }
        }

        return count;
    }
}
