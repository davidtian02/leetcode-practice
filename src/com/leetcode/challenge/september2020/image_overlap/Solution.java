package com.leetcode.challenge.september2020.image_overlap;

import java.util.Arrays;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3450/
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        // convert both to 1d array of ints
        int[] first = flattenMatrix(A);
        int[] second = flattenMatrix(B);

        // then go through each time and move right by 1 bit. and then repeat without first num, then repeat without second num...etc.
        int max = 0;
        for (int i=0; i<first.length; i++) {
            max = Math.max(max, checkOverlapMoveRight(first, second, i));
            max = Math.max(max, checkOverlapMoveRight(second, first, i));
        }
        return max;
    }

    private int checkOverlapMoveRight(int[] first, int[] second, int rowOffset) {
        int max = 0;
        for (int i=0; i<first.length; i++) {
            int temp = countOverlapsByRow(first, second, rowOffset, i);
            max = Math.max(temp, max);
        }
        return max;
    }

    private int countOverlapsByRow(int[] first, int[] second, int rowOffset, int colOffset) {
        int count = 0;
        for (int r=rowOffset; r<first.length; r++) {
            int a = first[r-rowOffset] >> colOffset;
            int b = second[r];
            count += countOnes(a, b);
        }
        return count;
    }

    private int countOnes(int a, int b) {
        return Integer.bitCount((a & b));
    }

    private int[] flattenMatrix(int[][] matrix) {
        return Arrays.stream(matrix).mapToInt((int[] bits) -> Arrays.stream(bits).reduce(0, (n, b) -> (n << 1) + b)).toArray();
    }

    public static void main(String... args) {
        Solution runner = new Solution();
//        test1(runner);
        test2(runner);
    }

    private static void test1(Solution runner) {
        int[][] a = new int[][]{
                {1, 0, 1},
                {1, 0, 1},
                {1, 1, 1},
        };
        int[][] b = new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 0},
        };
        System.out.println(runner.largestOverlap(a, b));
    }

    private static void test2(Solution runner) {
        int[][] a = new int[][]{
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0},
        };
        int[][] b = new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1},
        };
        System.out.println(runner.largestOverlap(a, b));
    }
}
