package com.leetcode.problems.medium.minimum_path_sum;

// https://leetcode.com/problems/minimum-path-sum/submissions/
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }

        // dp problem. first init edges. going right and going down on 1 row and 1 col only.
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                int left = grid[i - 1][j];
                int top = grid[i][j - 1];
                int current = grid[i][j];
                grid[i][j] = Math.min(left + current, top + current);
            }
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        int answer = runner.minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1},
        });
        System.out.println(answer);
    }
}
