package com.leetcode.problems.medium.search_a_2d_matrix;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = findRow(matrix, 0, matrix.length-1, target);
        if (row < 0) {
            return false;
        }

        return findCol(matrix[row], 0, matrix[row].length - 1, target);
    }

    private boolean findCol(int[] row, int start, int end, int target) {
        int mid = ((end - start) >> 1) + start;
        if (row[start] == target || row[end] == target || row[mid] == target) {
            return true;
        }

        if (start >= end) {
            return false;
        }

        if (target > row[mid]) {
            return findCol(row, mid+1, end, target);
        } else {
            return findCol(row, start, mid-1, target);
        }
    }

    private int findRow(int[][] matrix, int start, int end, int target) {
        int rightMostIndex = matrix[end].length - 1;
        int mid = ((end-start)/2) + start;

        if (start == end) {
            return (target >= matrix[start][0] && target <= matrix[end][rightMostIndex]) ? start : -1;
        }

        if (target >= matrix[start][0] && target <= matrix[mid][rightMostIndex]) { // in left
            return findRow(matrix, start, mid, target);
        } else if (target >= matrix[mid+1][0] && target <= matrix[end][rightMostIndex]) { // go right
            return findRow(matrix, mid+1, end, target);
        } else {
            return -1;
        }
    }

    public static void main(String... args) {
//        test1();
//        test2();
        test3();
    }

    private static void test1() {
        Solution solution = new Solution();
        int[][] matrix = new int[][] {
            {1,3,5,7},
            {10,11,16,20},
            {23,30,34,60}
        };

        System.out.println(solution.searchMatrix(matrix, 3));
    }

    private static void test2() {
        Solution solution = new Solution();
        int[][] matrix = new int[][] {
            {1,3,5,7},{10,11,16,20},{23,30,34,60}
        };

        System.out.println(solution.searchMatrix(matrix, 13));
    }

    private static void test3() {
        Solution solution = new Solution();
        int[][] matrix = new int[][] {
            {1,1}
        };

        System.out.println(solution.searchMatrix(matrix, 0));
    }
}