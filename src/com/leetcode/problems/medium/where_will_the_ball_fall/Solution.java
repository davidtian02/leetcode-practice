package com.leetcode.problems.medium.where_will_the_ball_fall;

class Solution {
    public int[] findBall(int[][] grid) {
        int exitColumns[] = new int[grid[0].length];
        for (int i=0; i<exitColumns.length; i++) {
            exitColumns[i] = dropBall(grid, i);
        }
        return exitColumns;
    }

    private int dropBall(int[][] grid, int col) {
        for (int row=0; row<grid.length; row++) {
            int current = grid[row][col];
            if ((col+current < 0) || (col+current == grid[0].length) // stuck at wall
                    || (grid[row][col+current] != grid[row][col]) // stuck in V shape
                    ) {
                return -1; // either wall stuck
            }
            col += current;
        }

        return col;
    }
}
