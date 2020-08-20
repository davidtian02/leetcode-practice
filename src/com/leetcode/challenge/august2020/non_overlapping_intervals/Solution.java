package com.leetcode.challenge.august2020.non_overlapping_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/551/week-3-august-15th-august-21st/3425/
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        List<Interval> sorted = sortList(intervals);
        return countRemoved(sorted);
    }

    private int countRemoved(List<Interval> sorted) {
        int count = 0;
        Interval prev = sorted.get(0);
        for (int i=1; i<sorted.size(); i++) {
            Interval current = sorted.get(i);
            if (current.start < prev.end) {
                count++;
            } else {
                prev = current;
            }
        }
        return count;
    }

    private List<Interval> sortList(int[][] intervals) {
        List<Interval> result = new ArrayList<>(intervals.length);
        for (int[] i : intervals) {
            result.add(new Interval(i[0], i[1]));
        }
        Collections.sort(result);
        return result;
    }

    static class Interval implements Comparable<Interval> {
        int start, end;
        Interval(int a, int b) {
            start = a;
            end = b;
        }
        @Override
        public int compareTo(Interval other) {
            return Integer.compare(this.end, other.end);
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
//        System.out.println(runner.eraseOverlapIntervals(new int[][]{
//                {1,2},{2,3},{3,4},{1,3}
//        }));
        System.out.println(runner.eraseOverlapIntervals(new int[][]{
                {0,2},{1,3},{2,4},{3,5},{4,6}
        }));

    }
}