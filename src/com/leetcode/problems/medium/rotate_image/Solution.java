package com.leetcode.problems.medium.rotate_image;

class Solution {
    public void rotate(int[][] matrix) {
        for (int i=0; i<(matrix.length>>1); i++) {
            rotate3(matrix, i, matrix.length-1-i);
        }
    }

    private void rotate3(int[][] matrix, int left, int right) {
        int temp;
        for (int i=left; i<right; i++) {
            temp = matrix[left][i];
            matrix[left][i] = matrix[right-(i-left)][left];
            matrix[right-(i-left)][left] = matrix[right][right-(i-left)];
            matrix[right][right-(i-left)] = matrix[i][right];
            matrix[i][right] = temp;
        }
    }

    private void rotate2(int[][] matrix, int left, int right) {
        for (int i=left,j=right; i<right; i++,j--) {
            int topCopy = matrix[left][i];
            int rightCopy = matrix[i][right];
            int bottomCopy = matrix[right][j];
            int leftCopy = matrix[j][left];

            // then swap
            matrix[left][i] = leftCopy;
            matrix[i][right] = topCopy;
            matrix[right][j] = rightCopy;
            matrix[j][left] = bottomCopy;
        }
    }

    private void rotate(int[][] matrix, int left, int right) {
        // make 4 swaps?
        int[] topCopy = new int[right-left+1];
        int[] bottomCopy = new int[right-left+1];
        int[] leftCopy = new int[right-left+1];
        int[] rightCopy = new int[right-left+1];

        // first copy
        for (int i=left; i<=right; i++) { // top
            topCopy[i-left] = matrix[left][i];
        }
        for (int i=left; i<=right; i++) { // right
            rightCopy[i-left] = matrix[i][right];
        }
        for (int i=right; i>=left; i--) { // bottom
            bottomCopy[right-i] = matrix[right][i];
        }
        for (int i=right; i>=left; i--) { // left
            leftCopy[right-i] = matrix[i][left];
        }

        // then rotate
        for (int i=left; i<=right; i++) { // leftCopy -> matrix top
            matrix[left][i] = leftCopy[i-left];
        }
        for (int i=left; i<=right; i++) { // topCopy -> matrix right
            matrix[i][right] = topCopy[i-left];
        }
        for (int i=right; i>=left; i--) { // rightCopy -> matrix bottom
            matrix[right][i] = rightCopy[right-i];
        }
        for (int i=right; i>=left; i--) { // bottom Copy -> matrix left
            matrix[i][left] = bottomCopy[right-i];
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        int[][] matrix = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        runner.rotate(matrix);
        System.out.println("done");
    }
}

// (0,0), (0,1), (0,2)
// (1,0), (1,1), (1,2)
// (2,0), (2,1), (2,2)

// (2,0), (1,0), (0,0)
// (2,1), (1,1), (0,1)
// (2,2), (1,2), (0,2)

// can rotate 1 at a time, from arrs to half arrs len.
