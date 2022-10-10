package com.leetcode.problems.medium.set_matrix_zero;

class Solution {
    public void setZeroes(int[][] matrix) {
        int colEnd = matrix.length - 1;
        int rowEnd = matrix[0].length - 1;


        boolean shoudlWipeFirstRow = false;
        for (int i=0; i<matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                shoudlWipeFirstRow = true;
                break;
            }
        }

        boolean shouldWipeFirstCol = false;
        for (int i=0; i<=colEnd; i++) {
            if (matrix[i][0] == 0) {
                shouldWipeFirstCol = true;
                break;
            }
        }

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                // mark row head and col head if you ever meet a 0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // wipe all rows except the very first row
        for (int i=1; i<=colEnd; i++) {
            if (matrix[i][0] == 0) {
                wipeRow(matrix, i);
            }
        }

        // wipe all column except first column
        for (int i=1; i<=rowEnd; i++) {
            if (matrix[0][i] == 0) {
                wipeCol(matrix, i);
            }
        }

        // check first row
        if (shoudlWipeFirstRow) {
            wipeRow(matrix, 0);
        }

        // check first col
        if (shouldWipeFirstCol) {
            wipeCol(matrix, 0);
        }
    }

    private void wipeRow(int[][] matrix, int r) {
        for (int i=0; i<matrix[0].length; i++) {
            matrix[r][i] = 0;
        }
    }

    private void wipeCol(int[][] matrix, int c) {
        for (int i=0; i<matrix.length; i++) {
            matrix[i][c] = 0;
        }
    }
}