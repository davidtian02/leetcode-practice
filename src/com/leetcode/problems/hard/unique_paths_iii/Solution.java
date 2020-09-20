package com.leetcode.problems.hard.unique_paths_iii;

import java.util.*;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/556/week-3-september-15th-september-21st/3466/
class Solution {
    private static final int VISITED = -2;

    public int uniquePathsIII(int[][] grid) {
        int startX=-2, startY=-2;
        int zeroCount = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                switch(grid[i][j]) {
                    case 0: zeroCount++; break;
                    case 1: startX = i; startY = j; break;
                }
            }
        }

        return dfs(grid, startX, startY, zeroCount, 0);
    }

    private int dfs(int[][] grid, int x, int y, final int zerosNeeded, int zeroStepCount) {
        if (invalidStep(grid, x, y)) {
            return 0;
        }

        if (grid[x][y] == 2 && zerosNeeded == zeroStepCount) {
            return 1;
        }

        if (grid[x][y] == 0) {
            zeroStepCount++;
        }

        int paths = 0;
        int originalValue = grid[x][y];
        grid[x][y] = VISITED;
        paths += dfs(grid, x-1, y, zerosNeeded, zeroStepCount); // left
        paths += dfs(grid, x+1, y, zerosNeeded, zeroStepCount); // right
        paths += dfs(grid, x, y-1, zerosNeeded, zeroStepCount); // up
        paths += dfs(grid, x, y+1, zerosNeeded, zeroStepCount); // down
        grid[x][y] = originalValue;
        return paths;
    }

    private boolean invalidStep(int[][] grid, int x, int y) {
        return x<0 || y<0 || x>=grid.length || y >= grid[0].length || grid[x][y]<0; // visited OR obstacle
    }

    // using DFS with visited stack

    public int uniquePathsIIIdfs(int[][] grid) {
        int startX=-2, startY=-2;
        int zeroCount = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                switch(grid[i][j]) {
                    case 0: zeroCount++; break;
                    case 1: startX = i; startY = j; break;
                }
            }
        }

        return traverse(grid, new Pair(startX, startY), zeroCount, 0, new HashSet<>());
    }

    private int traverse(int[][] grid, Pair next, final int zerosNeeded, int zeroStepCount, HashSet<Pair> visited) {
        Pair current = next;
        if (invalidStep(grid, current) || visited.contains(current)) {
            return 0;
        }

        if (grid[current.x][current.y] == 2 && zerosNeeded == zeroStepCount) {
            return 1;
        }

        if (grid[current.x][current.y] == 0) {
            zeroStepCount++;
        }

        int paths = 0;
        visited.add(current);
        paths += traverse(grid, new Pair(current.x-1, current.y), zerosNeeded, zeroStepCount, visited); // left
        paths += traverse(grid, new Pair(current.x+1, current.y), zerosNeeded, zeroStepCount, visited); // right
        paths += traverse(grid, new Pair(current.x, current.y-1), zerosNeeded, zeroStepCount, visited); // up
        paths += traverse(grid, new Pair(current.x, current.y+1), zerosNeeded, zeroStepCount, visited); // down
        visited.remove(current);
        return paths;
    }

    private boolean invalidStep(int[][] grid, Pair coordinate) {
        return coordinate.x<0 || coordinate.y<0 || coordinate.x>=grid.length || coordinate.y >= grid[0].length
                || grid[coordinate.x][coordinate.y]<0;
    }

    static class Pair {
        int x, y;
        Pair(int ox, int oy) {
            x = ox;
            y = oy;
        }
        @Override
        public int hashCode() {
            return new AbstractMap.SimpleEntry<>(x, y).hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof Pair && x == ((Pair)other).x && y == ((Pair)other).y;
        }
    }

    // using BFS

    public int uniquePathsIIIAlternative(int[][] grid) {
        int startX=-2, startY=-2;
        int endX=-2, endY=-2;
        int zeroCount = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                switch(grid[i][j]) {
                    case 0: zeroCount++; break;
                    case 1: startX = i; startY = j; break;
                    case 2: endX = i; endY = j; break;
                }
            }
        }
        Map.Entry<Integer, Integer> start = new AbstractMap.SimpleEntry<>(startX, startY);
        Map.Entry<Integer, Integer> end = new AbstractMap.SimpleEntry<>(endX, endY);

        return findAllPaths(grid, start, end, zeroCount);
    }

    private int findAllPaths(int[][] grid, Map.Entry<Integer, Integer> start, Map.Entry<Integer, Integer> end, int zeroCount) {
        // bfs through.
        Queue<Path> q = new LinkedList<>();
        Path p = new Path(grid, start, end, new HashSet<>(Arrays.asList(start)));
        q.add(p);
        int count = 0;
        while (!q.isEmpty()) {
            Path current = q.remove();
            if (current.isDestination()) {
                if (current.steppedAllZeros(zeroCount)) {
                    count++;
                }
            } else {
                List<Map.Entry<Integer, Integer>> nextSteps = current.nextSteps();
                if (!nextSteps.isEmpty()) {
                    for (Map.Entry<Integer, Integer> n : nextSteps) {
                        Path np = current.moveStep(n);
                        q.add(np);
                    }
                }
            }
        }
        return count;
    }

    static class Path {
        int[][] grid;
        Map.Entry<Integer, Integer> current;
        Map.Entry<Integer, Integer> end;
        Set<Map.Entry<Integer, Integer>> visited;
        Path(int[][] g, Map.Entry<Integer, Integer> s, Map.Entry<Integer, Integer> e, Set<Map.Entry<Integer, Integer>> v) {
            grid = g;
            current = s;
            end = e;
            visited = v;
        }

        boolean isDestination() {
            return current.getKey() == end.getKey() && current.getValue() == end.getValue();
        }

        List<Map.Entry<Integer, Integer>> nextSteps() {
            // left right up down
            List<Map.Entry<Integer, Integer>> result = new LinkedList<>();
            int x = current.getKey();
            int y = current.getValue();
            if (x-1>=0 && grid[x-1][y] != -1 && grid[x-1][y] != 1 && !visited.contains(new AbstractMap.SimpleEntry<>(x-1,y))) {
                result.add(new AbstractMap.SimpleEntry<>(x-1, y));
            }
            if (x+1<grid.length && grid[x+1][y] != -1 && grid[x+1][y] != 1 && !visited.contains(new AbstractMap.SimpleEntry<>(x+1,y))) {
                result.add(new AbstractMap.SimpleEntry<>(x+1, y));
            }
            if (y+1<grid[0].length && grid[x][y+1] != -1 && grid[x][y+1] != 1 && !visited.contains(new AbstractMap.SimpleEntry<>(x,y+1))) {
                result.add(new AbstractMap.SimpleEntry<>(x, y+1));
            }
            if (y-1>=0 && grid[x][y-1] != -1 && grid[x][y-1] != 1 && !visited.contains(new AbstractMap.SimpleEntry<>(x,y-1))) {
                result.add(new AbstractMap.SimpleEntry<>(x, y-1));
            }
            return result;
        }

        Path moveStep(Map.Entry<Integer, Integer> to) {
            Set<Map.Entry<Integer, Integer>> copy = new HashSet<>(visited);
            copy.add(to);
            return new Path(grid, to, end, copy);
        }

        boolean steppedAllZeros(int zeroCount) {
            return visited.size()-2 == zeroCount;
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.uniquePathsIII(new int[][]{
                {1,0,0,0},
                {0,0,0,0},
                {0,0,2,-1},
        }));

        System.out.println(runner.uniquePathsIII(new int[][]{
                {1,0,0},
                {0,0,0},
                {0,0,2},
        }));

        System.out.println(runner.uniquePathsIII(new int[][]{
                {1},
                {0},
                {0},
                {0},
                {2},
        }));
    }
}
