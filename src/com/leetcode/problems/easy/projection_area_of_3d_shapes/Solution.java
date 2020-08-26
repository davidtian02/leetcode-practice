package com.leetcode.problems.easy.projection_area_of_3d_shapes;

// https://leetcode.com/problems/projection-area-of-3d-shapes/
class Solution {
    public int projectionArea(int[][] grid) {
        int z = sumZ(grid);
        int y = sumY(grid);
        int x = sumX(grid);
        return x + y + z;
    }

    // TODO prolly combine some of these operations
    private int sumZ(int[][] grid) {
        int count = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private int sumX(int[][] grid) {
        int count = 0;
        for (int i=0; i<grid.length; i++) {
            int maxX = 0;
            for (int j=0; j<grid[0].length; j++) {
                if (grid[j][i] > maxX) {
                    maxX = grid[j][i];
                }
            }
            count += maxX;
        }
        return count;
    }

    private int sumY(int[][] grid) {
        int count = 0;
        for (int i=0; i<grid.length; i++) {
            int maxY = 0;
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] > maxY) {
                    maxY = grid[i][j];
                }
            }
            count += maxY;
        }
        return count;
    }
}