package com.leetcode.challenge.august2020.rotting_oranges;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/550/week-2-august-8th-august-14th/3418/
public class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<Cell> rotten = new LinkedList<>();
        Set<Cell> fresh = new HashSet<>();
        Set<Cell> visited = new HashSet<>();
        init(grid, rotten, fresh, visited);

        return simulate(grid, rotten, fresh, visited);
    }

    private int simulate(int[][] grid, Queue<Cell> rotten, Set<Cell> fresh, Set<Cell> visited) {
        int time = 0;
        if (fresh.isEmpty()) {
            return 0;
        }

        while (!rotten.isEmpty()) {
            int len = rotten.size();
            for (int i=0; i<len; i++) {
                Cell current = rotten.remove();
                for (Cell neighbor : current.neighbors()) {
                    if (isValid(grid, neighbor) && !visited.contains(neighbor)) {
                        grid[neighbor.x][neighbor.y] = 2; // rot
                        visited.add(neighbor);
                        fresh.remove(neighbor);
                        rotten.add(neighbor);
                    }
                }
            }
            time++;
        }

        if (!fresh.isEmpty()) {
            // fresh fruits that never got rotten
            return -1;
        }

        return time -1;
    }

    private boolean isValid(int[][] grid, Cell neighbor) {
        // within bounds, and is fresh
        if (neighbor.x >=0 && neighbor.y >= 0 && neighbor.x < grid.length && neighbor.y < grid[0].length) {
            return grid[neighbor.x][neighbor.y] == 1;
        }

        return false;
    }

    private void init(int[][] grid, Queue<Cell> rotten, Set<Cell> freshOranges, Set<Cell> visited) {
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                Cell c = new Cell(i, j);
                switch (grid[i][j]) {
                    case 0: break;
                    case 1: freshOranges.add(c); break;
                    case 2: rotten.add(c); visited.add(c); break;
                    default: throw new IllegalArgumentException(" cells should only have val 0-2");
                }
            }
        }
    }

    static class Cell {
        Map.Entry<Integer, Integer> coordinates;
        int x, y;
        Cell(int i, int j) {
            coordinates = new AbstractMap.SimpleEntry<>(i, j);
            x = i;
            y = j;
        }

        Cell[] neighbors() {
            return new Cell[]{new Cell(x-1, y), new Cell(x+1, y), new Cell(x, y-1), new Cell(x, y+1)};
        }

        @Override
        public boolean equals(Object obj) {
            return x == ((Cell)obj).x && ((Cell)obj).y == y;
        }

        @Override
        public int hashCode() {
            return coordinates.hashCode();
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();

        int[][] grid = new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
        System.out.println(runner.orangesRotting(grid));
    }
}
