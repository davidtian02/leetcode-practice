package com.leetcode.problems.medium.search_a_2d_matrix_ii;

class Solution {
    // 3:08
    public boolean searchMatrix(int[][] matrix, int target) {
        int[] coordinates = findSubgrid(matrix, target);
        if (coordinates == null) {
            return false;
        }
        return search(matrix, coordinates, target);
    }

    private int[] findSubgrid(int[][] matrix, int target) {
        Integer startRow=null, endRow=null;
        for (int i=0; i<matrix.length; i++) {
            int[] row = matrix[i];
            if (target > row[row.length - 1]) {
                continue;
            } else if (target < row[0]) {
                break;
            }

            if (startRow == null) {
                startRow = i;
            }

            endRow = i;
        }

        if (startRow == null) {
            return null; // can't find
        }

        Integer startCol=null, endCol=null;
        for (int i=0; i<matrix[0].length; i++) {
            if (target > matrix[matrix.length-1][i]) {
                continue;
            } else if (target < matrix[0][i]) {
                break;
            }

            if (startCol == null) {
                startCol = i;
            }

            endCol = i;
        }

        if (startCol == null) {
            return null;
        }

        return new int[]{startRow, endRow, startCol, endCol};
    }

    private boolean search(int[][] matrix, int[] coordinates, int target) {
        int row1=coordinates[0],row2=coordinates[1],col1=coordinates[2],col2=coordinates[3];
        for (int i=row1; i<=row2; i++) {
            if (binarySearchRow(matrix[i], col1, col2, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearchRow(int[] row, int start, int end, int target) {
        int mid = ((end - start) >> 1) + start;
        if (start >= end) {
            return row[start] == target;
        }

        if (target == row[start] || target == row[end] || target == row[mid]) {
            return true;
        }

        return binarySearchRow(row, start + 1, mid - 1, target) || binarySearchRow(row, mid + 1, end - 1, target);
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        Solution runner = new Solution();
        int[][] matrix = new int[][] {
                {-5}
        };
        System.out.println(runner.searchMatrix(matrix, -2));
    }

    private static void test2() {
        Solution runner = new Solution();
        int[][] matrix = new int[][] {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(runner.searchMatrix(matrix, 5));
    }

    private static void test3() {
        Solution runner = new Solution();
        int[][] matrix = new int[][] {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(runner.searchMatrix(matrix, 20));
    }
}
