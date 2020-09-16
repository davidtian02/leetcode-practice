package com.leetcode.problems.medium.unique_paths;

// https://leetcode.com/problems/unique-paths/
class Solution {
    public int uniquePaths(int m, int n) {
        if (m<1 || n<1) {
            return 0;
        }

        int[][] cache = new int[m][n];
        return maxCombos(m, n, cache, m-1, n-1);
    }

    private int maxCombos(int m, int n, int[][] cache, int x, int y) {
        if (x == 0 || y == 0) {
            return 1;
        }
        if (cache[x][y] != 0) {
            return cache[x][y];
        }

        int uniqPaths = maxCombos(m, n, cache, x-1, y) + maxCombos(m, n, cache, x, y-1);
        cache[x][y] = uniqPaths;
        return uniqPaths;
    }
}
