package com.leetcode.problems.medium.count_submatrices_with_all_ones;

import java.util.Arrays;

// https://leetcode.com/problems/count-submatrices-with-all-ones/
// basing this solution on discussions. couldn't actually figure out without help
public class Solution {
    public int numSubmat(int[][] mat) {
        int count = 0;

        for (int i=0; i<mat.length; i++) {
            // this is an array used for collecting all the "1" values above it.
            int[] consecutiveOnesCollector = Arrays.copyOf(mat[i], mat[i].length);
            count += helper(i, consecutiveOnesCollector, mat);
        }

        return count;
    }

    private int helper(int row, int[] consecutiveOnesCollector, int[][] m) {
        int count = countOneRow(consecutiveOnesCollector);

        for (int i=row+1; i<m.length; i++) {
            for (int j=0; j<m[i].length; j++) {
                // we want to count only the contiguous submatrices with 1's in the above column as well. so we
                // remove the columns that are no longer contiguous
                if (m[i][j] == 0 || consecutiveOnesCollector[j] == 0) {
                    consecutiveOnesCollector[j] = 0;
                }
            }
            count += countOneRow(consecutiveOnesCollector);
        }
        return count;
    }

    // there is some paper math here behind this - turns out, there's mathmatical pattern to this counting. see:
    /**
     1 -> 1
     1, 1 -> 3
     1, 1, 1 -> 6
     1, 1, 1, 1, -> 10 = (1+2+3+4)
     1, 1, 1, 1, 1 -> 15   = (1+2+3+4+5)
     1, 1, 1, 1, 1, 1 -> 21 = (1+2+...+n)
     1, ... n -> 1 + ...n
     */
    private int countOneRow(int[] consecutiveOnesCollector) {
        int count = 0;
        int temp = 0;
        for (int n : consecutiveOnesCollector) {
            if (n != 0) {
                temp++;
            } else {
                temp = 0;
            }
            count += temp;
        }
        return count;
    }

    public static void main(String... args) {
        Solution tests = new Solution();
        System.out.println(tests.numSubmat(new int[][]{{1}}));
        System.out.println(tests.numSubmat(new int[][]{
                {1,1}
        }));
        System.out.println(tests.numSubmat(new int[][]{
                {1,1,1}
        }));
        System.out.println(tests.numSubmat(new int[][]{
                {0,1},
                {1,0},
        }));
    }
}
