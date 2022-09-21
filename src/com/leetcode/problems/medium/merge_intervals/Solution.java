package com.leetcode.problems.medium.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.addAll(Arrays.asList(intervals));
        ArrayList<int[]> result = new ArrayList<>();
        int[] previous = pq.remove();

        while (!pq.isEmpty()) {
            int[] next = pq.remove();
            if (previous[1] < next[0]) {
                result.add(previous);
                previous = next;
            } else {
                previous[1] = Math.max(previous[1], next[1]); // absorbed next
            }
        }

        result.add(previous);
        return result.toArray(new int[result.size()][2]);
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    private static void test1() {
        Solution runner = new Solution();
        int[][] intervals = new int[][] {
                {0, 1},
                {3, 4},
        };
        System.out.println(Arrays.deepToString(runner.merge(intervals)));
    }

    private static void test2() {
        Solution runner = new Solution();
        int[][] intervals = new int[][] {
                {0, 4},
                {2, 6},
        };
        System.out.println(Arrays.deepToString(runner.merge(intervals)));
    }

    private static void test3() {
        Solution runner = new Solution();
        int[][] intervals = new int[][] {
                {2, 3},
                {0, 1},
        };
        System.out.println(Arrays.deepToString(runner.merge(intervals)));
    }

    private static void test4() {
        Solution runner = new Solution();
        int[][] intervals = new int[][] {
                {1, 11},
                {0, 10},
                {5, 8},
        };
        System.out.println(Arrays.deepToString(runner.merge(intervals)));
    }

    private static void test5() {
        Solution runner = new Solution();
        int[][] intervals = new int[][] {
                {0, 1},
                {1, 2},
        };
        System.out.println(Arrays.deepToString(runner.merge(intervals)));
    }
}
