package com.leetcode.problems.medium.largest_plus_sign;

class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] matrix = constructMatrix(n, mines);
        int[][][] cache = countOnes(matrix, matrix.length);
        return findMax(cache);
    }

    private int[][] constructMatrix(int n, int[][] mines) {
        int[][] matrix = new int[n][n];
        for (int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                matrix[i][j] = 1;
            }
        }
        for (int[] m : mines) {
            matrix[m[0]][m[1]] = 0;
        }
        return matrix;
    }

    private int[][][] countOnes(int[][] matrix, int n) {
        int[][][] cache = new int[n][n][4];// compute 4 matrix cache of length of max # of 1's going left, right, up, down
        // going left:
        for (int row=0; row<cache.length; row++) {
            cache[row][0][0] = matrix[row][0];
        }
        for (int row=0; row<cache.length; row++) {
            for (int col=1; col<cache[0].length; col++) {
                cache[row][col][0] = matrix[row][col] == 0 ? 0 : 1 + cache[row][col-1][0];
            }
        }

        // going right:
        for (int row=0; row<cache.length; row++) {
            cache[row][n-1][1] = matrix[row][n-1];
        }
        for (int row=0; row<cache.length; row++) {
            for (int col=n-2; col>=0; col--) {
                cache[row][col][1] = matrix[row][col] == 0 ? 0 : 1 + cache[row][col+1][1];
            }
        }

        // going up:
        for (int col=0; col<cache[0].length; col++) {
            cache[0][col][2] = matrix[0][col];
        }
        for (int col=0; col<cache[0].length; col++) {
            for (int row=1; row<n; row++) {
                cache[row][col][2] = matrix[row][col] == 0 ? 0 : 1 + cache[row-1][col][2];
            }
        }

        // going down:
        for (int col=0; col<cache[0].length; col++) {
            cache[n-1][col][3] = matrix[n-1][col];
        }
        for (int col=0; col<cache[0].length; col++) {
            for (int row=n-2; row>=0; row--) {
                cache[row][col][3] = matrix[row][col] == 0 ? 0 : 1 + cache[row+1][col][3];
            }
        }

        return cache;
    }

    private int findMax(int[][][] cache) {
        int max = 0;
        for (int i=0; i<cache.length; i++) {
            for (int j=0; j<cache.length; j++) {
                int count = Math.min(cache[i][j][0], cache[i][j][1]);
                count = Math.min(count, cache[i][j][2]);
                count = Math.min(count, cache[i][j][3]);
                max = Math.max(max, count);
            }
        }
        return max;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        int result = runner.orderOfLargestPlusSign(5, new int[][]{{4,2}});
        System.out.println(result);
    }
}
