package com.leetcode.problems.medium.spiral_matrix;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> solution = new LinkedList<>();
        Direction direction = Direction.RIGHT;
        int leftStop=-1, rightStop=matrix[0].length, downStop=matrix.length, upStop=0;
        int row=0, col=0, steps=(matrix.length) * matrix[0].length;
        int step=0;

        while(step < steps) {
            solution.add(matrix[row][col]);

            switch (direction) {
                case RIGHT:
                    if (col+1 < rightStop) {
                        col++;
                    } else {
                        direction = Direction.DOWN;
                        rightStop--;
                        row++;
                    }
                    break;
                case DOWN:
                    if (row+1 < downStop) {
                        row++;
                    } else {
                        direction = Direction.LEFT;
                        downStop--;
                        col--;
                    }
                    break;
                case LEFT:
                    if (col-1 > leftStop) {
                        col--;
                    } else {
                        direction = Direction.UP;
                        leftStop++;
                        row--;
                    }
                    break;
                case UP:
                    if (row-1 > upStop) {
                        row--;
                    } else {
                        direction = Direction.RIGHT;
                        upStop++;
                        col++;
                    }
                    break;
                default:
                    throw new RuntimeException("invalid direction?");
            }
            step++;
        }

        return solution;
    }


    enum Direction {
        RIGHT, DOWN, LEFT, UP;
    }
}