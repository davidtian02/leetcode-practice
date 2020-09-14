package com.leetcode.challenge.september2020.insert_interval;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/555/week-2-september-8th-september-14th/3458/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // edges
        // use stack. push new interval. merge with previous, or swap.
        Stack<int[]> stack = new Stack<>();
        stack.push(newInterval);
        for (int[] a : intervals) {
            int[] b = stack.peek();
            if (canMerge(a, b)) {
                stack.pop();
                stack.push(merge(a, b));
            } else {
                if (isBefore(a, b)) {
                    b = stack.pop();
                    stack.push(a);
                    stack.push(b);
                } else {
                    stack.push(a);
                }
            }
        }

        return convert(stack);
    }

    private boolean canMerge(int[] a, int[] b) {
        return !(a[1] < b[0] || b[1] < a[0]);
    }

    private int[] merge(int[] a, int[] b) {
        return new int[]{Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

    private boolean isBefore(int[] a, int[] b) {
        return a[1] < b[0];
    }

    private int[][] convert(Stack<int[]> stack) {
        int[][] result = new int[stack.size()][2];
        for (int i=result.length-1; i>=0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        int[][] result = runner.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        System.out.println(Arrays.toString(result));
    }
}
