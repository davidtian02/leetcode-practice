package com.leetcode.problems.medium.pacific_atlantic_water_flow;

import java.util.*;

class Solution {

    // for cache: 'b' == both, 'p' = pacific, 'a' == atlantic, 'u' == unsure, 'c' == can't do
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        char[][] cache = initCache(heights);
        traverse(heights, cache);
        return convert(cache);
    }

    private char[][] initCache(int[][] heights) {
        char[][] cache = new char[heights.length][heights[0].length];
        for (int i=0; i<cache.length; i++) {
            for (int j=0; j<cache[0].length; j++) {
                cache[i][j] = 'u';
            }
        }
        return cache;
    }

    private void traverse(int[][] grid, char[][] cache) {
        char[][] cachePacific = initCache(grid);

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (canFindPacific(grid, cachePacific, i, j, new HashSet<>())) {
                    // System.out.println("can find pacific: [" + i + "," + j + "]");
                    cachePacific[i][j] = 'p';
                }
            }
        }

        // printCache(cachePacific);

        char[][] cacheAtlantic = initCache(grid);
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (cachePacific[i][j] == 'p') {
                    if (canFindAtlantic(grid, cacheAtlantic, i, j, new HashSet<>())) {
                        // System.out.println("can find atlantic also: [" + i + "," + j + "]");
                        cacheAtlantic[i][j] = 'a';
                    }
                }
            }
        }

        // printCache(cacheAtlantic);

        // match both
        for (int i=0; i<cache.length; i++) {
            for (int j=0; j<cache[0].length; j++) {
                if (cachePacific[i][j] == 'p' && cacheAtlantic[i][j] == 'a') {
                    cache[i][j] = 'b';
                }
            }
        }
    }

    private void printCache(char[][] cache) {
        System.out.println(Arrays.deepToString(cache));
    }

    private boolean canFindPacific(int[][] grid, char[][] cache, int i, int j, Set<String> visited) {
        if (cache[i][j] == 'p') {
            return true;
        }
//        else if (cache[i][j] == 'c') {
//            return false;
//        }

        visited.add("" + i + "," + j);

        // left
        if (j-1 >= 0) {
            if ((!visited.contains("" + i + "," + (j-1))) && (grid[i][j] >= grid[i][j-1])) {
                if (canFindPacific(grid, cache, i, j-1, visited)) {
                    cache[i][j-1] = 'p';
                    return true;
                }
            }
        } else {
            cache[i][j] = 'p';
            return true;
        }

        // up
        if (i-1 >= 0) {
            if ((!visited.contains("" + (i-1) + "," + j)) && (grid[i][j] >= grid[i-1][j]) ) {
                if (canFindPacific(grid, cache, i-1, j, visited)) {
                    cache[i-1][j] = 'p';
                    return true;
                }
            }
        } else {
            cache[i][j] = 'p';
            return true;
        }

        // right
        if (j+1 < cache[i].length) {
            if ((!visited.contains("" + i + "," + (j+1))) && (grid[i][j] >= grid[i][j+1]) ) {
                if (canFindPacific(grid, cache, i, j+1, visited)) {
                    cache[i][j+1] = 'p';
                    return true;
                }
            }
        }

        // down
        if (i+1 < cache.length) {
            if ((!visited.contains("" + (i+1) + "," + j)) && (grid[i][j] >= grid[i+1][j])) {
                if (canFindPacific(grid, cache, i+1, j, visited)) {
                    cache[i+1][j] = 'p';
                    return true;
                }
            }
        }

        cache[i][j] = 'c';
        return false;
    }

    private boolean canFindAtlantic(int[][] grid, char[][] cache, int i, int j, Set<String> visited) {
        if (cache[i][j] == 'a') {
            return true;
        }
//        else if (cache[i][j] == 'c') {
//            return false;
//        }

        visited.add("" + i + "," + j);

        // right
        if (j+1 < cache[i].length) {
            if ((!visited.contains("" + i + "," + (j+1))) && (grid[i][j] >= grid[i][j+1]) ) {
                if (canFindAtlantic(grid, cache, i, j+1, visited)) {
                    cache[i][j+1] = 'a';
                    return true;
                }
            }
        } else {
            cache[i][j] = 'a';
            return true;
        }


        // down
        if (i+1 < cache.length) {
            if ((!visited.contains("" + (i+1) + "," + j)) && (grid[i][j] >= grid[i+1][j])) {
                if (canFindAtlantic(grid, cache, i+1, j, visited)) {
                    cache[i+1][j] = 'a';
                    return true;
                }
            }
        } else {
            cache[i][j] = 'a';
            return true;
        }

        // left
        if (j-1 >= 0) {
            if ((!visited.contains("" + i + "," + (j-1))) && (grid[i][j] >= grid[i][j-1]) ) {
                if (canFindAtlantic(grid, cache, i, j-1, visited)) {
                    cache[i][j-1] = 'a';
                    return true;
                }
            }
        }

        // up
        if (i-1 >= 0) {
            if ((!visited.contains("" + (i-1) + "," + j)) && (grid[i][j] >= grid[i-1][j]) ) {
                if (canFindAtlantic(grid, cache, i-1, j, visited)) {
                    cache[i-1][j] = 'a';
                    return true;
                }
            }
        }

        cache[i][j] = 'c';
        return false;
    }

    private List<List<Integer>> convert(char[][] results) {
        List<List<Integer>> coordinates = new LinkedList<>();
        for (int i=0; i<results.length; i++) {
            for (int j=0; j<results[0].length; j++) {
                if (results[i][j] == 'b') {
                    coordinates.add(Arrays.asList(i, j));
                }
            }
        }
        return coordinates;
    }

    public static void main(String... args) {
        int[][] map = new int[][]{{11,2,11,0,15,12,4,15,0,14,11,3,19,11,5,11,18,19,4,3,11,1,9,17,5,2,15,18,11,15},{12,10,8,15,4,7,4,5,7,8,5,12,3,3,10,12,16,15,17,13,13,16,0,0,17,17,11,3,14,0},{8,18,1,6,15,16,14,11,9,11,3,4,17,7,2,16,18,2,0,0,16,18,10,15,14,18,10,19,17,6},{14,17,4,13,13,6,16,1,3,18,18,18,4,1,15,4,0,9,19,3,6,7,19,13,11,11,10,19,3,15},{16,6,19,17,19,17,5,12,6,3,1,0,3,10,13,18,4,3,9,0,1,18,9,15,18,3,4,6,1,15},{1,2,12,9,9,7,17,0,1,14,18,1,5,3,0,7,2,19,7,19,1,11,1,3,2,4,0,3,16,18},{18,10,10,3,12,11,7,8,3,16,7,11,11,12,15,1,13,9,8,17,1,9,7,19,1,14,8,10,18,14},{5,19,9,4,10,14,1,5,11,16,11,3,5,4,19,8,11,16,19,12,6,3,18,16,17,8,11,19,7,14},{0,15,17,11,10,13,19,0,10,3,15,19,3,3,3,4,3,12,17,10,5,16,12,5,5,17,5,17,6,6},{8,19,9,3,13,8,13,17,4,12,13,8,13,12,10,10,16,7,2,8,17,3,7,1,7,16,11,19,13,19},{6,19,6,13,10,5,14,7,3,1,10,6,4,8,15,0,0,2,12,13,14,14,7,5,1,16,15,15,4,7},{7,7,11,14,2,4,14,2,2,0,6,11,15,14,11,13,2,3,14,9,16,3,8,15,2,18,15,15,2,2},{7,5,12,10,14,3,6,9,2,1,2,15,0,4,7,9,7,12,15,9,2,13,7,8,7,9,4,3,5,19},{11,9,1,8,0,15,1,6,5,11,14,19,6,11,0,12,1,6,8,7,0,1,2,9,14,4,5,8,3,16},{8,0,11,5,14,4,19,0,6,8,1,10,13,8,18,6,6,4,5,9,10,14,14,13,12,16,4,3,3,11},{0,9,6,19,16,4,5,10,13,19,8,15,14,7,13,11,17,18,14,18,19,11,0,4,12,11,2,8,17,14},{16,19,16,9,9,14,5,13,7,10,18,6,15,12,12,1,11,16,1,8,1,7,16,7,19,6,12,0,15,0},{2,4,18,15,13,9,4,18,19,5,16,7,10,1,7,7,4,4,10,8,13,15,9,4,16,13,6,3,13,7},{3,11,10,13,6,4,0,13,11,4,5,6,19,13,8,10,8,9,2,4,4,11,12,8,12,15,6,1,10,12},{7,6,19,3,2,14,15,6,9,1,6,14,4,15,13,9,14,7,10,12,17,18,6,4,12,4,1,6,6,12},{15,17,9,15,9,15,9,10,10,11,12,17,2,18,11,0,6,11,14,17,2,13,9,13,3,4,3,1,8,11},{17,13,12,17,4,19,19,7,7,13,19,10,4,16,1,18,14,2,9,18,2,8,3,1,10,9,12,6,2,11},{17,12,6,8,3,16,5,2,16,3,13,3,13,9,11,11,5,12,14,16,3,19,16,16,1,14,5,3,17,19},{1,4,0,3,1,17,5,15,2,19,12,7,18,13,1,0,7,2,9,18,10,18,8,9,13,13,8,10,14,14},{9,14,4,18,10,18,3,9,9,17,16,4,19,7,3,18,7,0,10,13,9,10,11,16,3,5,1,2,16,19},{8,10,13,8,7,2,9,4,16,15,5,4,15,7,9,7,15,2,6,17,14,3,13,3,4,15,13,10,8,16},{17,7,19,19,13,12,6,0,11,4,10,4,1,9,15,9,7,7,14,6,7,18,9,13,6,16,5,2,17,1},{2,7,0,4,8,18,4,11,13,4,11,12,3,18,11,2,4,18,3,3,17,9,18,11,9,15,14,19,7,17},{13,1,15,18,4,12,18,18,15,16,7,17,9,15,11,3,9,7,18,13,3,11,7,19,10,10,7,13,7,19},{17,17,14,3,19,7,1,13,9,3,6,16,10,8,14,8,17,18,12,11,4,11,10,15,9,0,4,12,7,15},{4,4,8,1,7,11,13,4,11,5,18,2,16,11,16,13,0,13,13,12,11,15,8,4,0,3,2,9,8,15},{17,4,13,5,3,17,14,4,7,6,6,11,16,18,2,0,3,12,1,5,12,16,3,14,4,16,5,8,15,9},{5,3,17,17,6,4,19,5,4,6,11,4,14,18,4,19,16,15,1,17,3,8,13,14,16,13,18,19,6,4},{15,0,8,15,6,6,11,8,18,2,4,10,18,16,15,8,1,5,9,13,7,19,12,2,9,18,1,15,12,8},{5,0,18,14,1,8,18,15,5,13,15,7,8,8,9,0,14,12,4,17,2,10,9,7,19,7,19,9,7,1},{7,4,16,16,13,4,3,6,15,11,14,7,3,0,5,15,10,13,18,18,11,6,7,9,19,13,4,2,7,9},{9,14,15,11,14,5,15,1,19,15,3,4,0,10,4,1,2,15,18,15,15,2,9,0,3,10,9,16,4,1},{14,13,17,19,0,13,15,9,16,18,5,6,16,16,6,10,14,15,17,5,9,2,5,11,19,19,11,6,15,14},{17,7,19,6,5,19,10,2,11,17,17,13,16,13,19,4,12,3,4,13,7,9,19,9,12,3,16,8,18,13}};
        Solution runner = new Solution();
        System.out.println(runner.pacificAtlantic(map));
    }
}
