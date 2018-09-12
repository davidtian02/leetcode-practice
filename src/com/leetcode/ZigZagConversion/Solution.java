package com.leetcode.ZigZagConversion;

// submisison here: https://leetcode.com/submissions/detail/175585320/
class Solution {
    // TODO can this be way more efficient? like skip letters in original string?
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }

        String result = "";
        char[][] grid = new char[numRows][findNumCols(s.length(), numRows)];
        fillGrid(grid, s);
        result = computeGrid(grid);
        return result;
    }

    private int findNumCols(int length, int numRows) {
        int sections = length / (numRows * 2 - 2);
        int mod = length % (numRows * 2 - 2);
        int remainderSection = mod <= numRows? 1 : 1+(mod-numRows);
        return sections*(numRows-1) + remainderSection;
    }

    private void fillGrid(char[][] grid, String s) {
        int bounceLimit = grid.length;
        int tempRow = 0;
        int tempCol = 0;
        boolean shouldFillVertical = true;
        for (int i=0; i<s.length(); i++) {
            if (shouldFillVertical) {
                grid[tempRow][tempCol] = s.charAt(i);
                tempRow++;
            } else {
                grid[tempRow][tempCol] = s.charAt(i);
                tempRow--;
                tempCol++;
            }

            if (tempRow >= bounceLimit) {
                shouldFillVertical = false;
                tempRow-=2;
                tempCol++;
            }

            if (tempRow == 0) {
                shouldFillVertical = true;
            }
        }
    }

    private String computeGrid(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[i][j] != '\u0000') {
                    sb.append(grid[i][j]);
                }
            }
        }

        return sb.toString();
    }
}
