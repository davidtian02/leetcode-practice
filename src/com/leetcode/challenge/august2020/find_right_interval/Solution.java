package com.leetcode.challenge.august2020.find_right_interval;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3438/
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        List<Interval> sorted = IntStream
                .range(0, intervals.length)
                .mapToObj(index -> new Interval(intervals[index][0], intervals[index][1], index))
                .sorted()
                .collect(Collectors.toList());
        return fillRightIntervals(sorted);
    }

    private int[] fillRightIntervals(List<Interval> intervals) {
        int[] result = new int[intervals.size()];

        for (int i=0; i<intervals.size(); i++) {
            intervals.get(i).indexOfRightInterval = findIndexOfRightInterval(intervals, i);
        }

        for (Interval interval : intervals) {
            result[interval.originalIndex] = interval.indexOfRightInterval;
        }

        return result;
    }

    private int findIndexOfRightInterval(List<Interval> intervals, int index) {
        Interval target = intervals.get(index);
        if (index == intervals.size()-1 || intervals.get(intervals.size()-1).start < target.end) {
            return -1;
        }

        return binarySearch(intervals, target, index+1, intervals.size()-1);
    }

    private int binarySearch(List<Interval> intervals, Interval target, int left, int right) {
        if (intervals.get(left).start >= target.end) {
            return intervals.get(left).originalIndex;
        }

        int mid = ((right-left)>>1) + left;
        if (intervals.get(mid).start < target.end) {
            // then can skip all to the left
            return binarySearch(intervals, target, mid+1, right);
        } else {
            return binarySearch(intervals, target, left, mid);
        }
    }

    static class Interval implements Comparable<Interval> {
        int start, end, originalIndex;
        int indexOfRightInterval = -1;
        Interval(int s, int e, int i) {
            start = s;
            end = e;
            originalIndex = i;
        }

        @Override
        public int compareTo(Interval o) {
            return Integer.compare(start, o.start);
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(Arrays.toString(runner.findRightInterval(new int[][]{
                new int[]{3, 4},
                new int[]{2, 3},
                new int[]{1, 2},
        })));
        System.out.println(Arrays.toString(runner.findRightInterval(new int[][]{
                new int[]{1, 4},
                new int[]{2, 3},
                new int[]{3, 4},
        })));
    }
}