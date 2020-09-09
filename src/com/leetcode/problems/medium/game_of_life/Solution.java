package com.leetcode.problems.medium.game_of_life;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/game-of-life/
class Solution {

    // we simulate inplace twice. first counting the neighbors and replacing current value. but also then we
    // need to mark previous state, which we can do with adding +10 or adding +20. add +10 if was previously dead
    // add +20 to mark previously alive
    public void gameOfLife(int[][] board) {
        countNeighbors(board);
        simulate(board);
    }

    private void countNeighbors(int[][] board) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                countNeighborAt(board, i, j);
            }
        }
    }

    private void countNeighborAt(int[][] board, int i, int j) {
        int offset = board[i][j] == 0 ? 10 : 20;
        List<Pair> neighbors = Arrays.asList(
                new Pair(i-1,j-1),
                new Pair(i-1,j),
                new Pair(i-1,j+1),
                new Pair(i,j-1),
                new Pair(i,j+1),
                new Pair(i+1,j-1),
                new Pair(i+1,j),
                new Pair(i+1,j+1)
        );
        List<Pair> validNeighbors = neighbors.stream().filter(n -> n.x >= 0 && n.x < board.length && n.y >= 0 && n.y < board[0].length).collect(Collectors.toList());

        int aliveNeighborCount=0;
        for (Pair p : validNeighbors) {
            int val = board[p.x][p.y];
            if (val < 10) {
                // never got looked at, so it's either 0 or 1
                aliveNeighborCount += val;
            } else {
                aliveNeighborCount += val < 20 ? 0 : 1;
            }
        }

        board[i][j] = offset + aliveNeighborCount;
    }

    private void simulate(int[][] board) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                int val = board[i][j];
                if (val >= 20) { // was live, and has more than 1 neighbor
                    val -= 20; // minus the offset
                    if (val < 2 || val > 3) { // under or over population
                        board[i][j] = 0; // dies
                    } else {
                        board[i][j] = 1; // lives. reset value to 1
                    }
                } else {
                    val -= 10; // minus the offset
                    if (val == 3) {
                        board[i][j] = 1; // necro it back to life
                    } else {
                        board[i][j] = 0; // reset back to default dead val
                    }
                }
            }
        }
    }

    public void gameOfLife2(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                copy[i][j] = determine(board, i, j);
            }
        }

        copyMatrix(copy, board);
    }

    private int determine(int[][] board, int i, int j) {
        int aliveNeighbors = 0, deadNeighbors = 0;
        // count directions
        List<Pair> neighbors = Arrays.asList(
                new Pair(i-1, j-1),
                new Pair(i-1, j),
                new Pair(i-1, j+1),
                new Pair(i, j-1),
                new Pair(i, j+1),
                new Pair(i+1, j-1),
                new Pair(i+1, j),
                new Pair(i+1, j+1)
        );
        // filter for valid
        neighbors = neighbors.stream().filter(a -> a.x >= 0 && a.x < board.length && a.y >= 0 && a.y < board[0].length).collect(Collectors.toList());

        for (Pair p : neighbors) {
            if (board[p.x][p.y] == 0) {
                deadNeighbors++;
            } else {
                aliveNeighbors++;
            }
        }

        if (aliveNeighbors < 2) {
            return 0;
        } else if (aliveNeighbors > 3) {
            return 0;
        } else if (board[i][j] == 0 && aliveNeighbors == 3){
            return 1;
        }

        return board[i][j];
    }

    private void copyMatrix(int[][] original, int[][] onto) {
        for (int i=0; i<original.length; i++) {
            for (int j=0; j<original[0].length; j++) {
                onto[i][j] = original[i][j];
            }
        }
    }

    static class Pair{
        int x, y;
        Pair(int i, int j){
            x = i;
            y = j;
        }
    }

    public static void main(String... args) {
//        test1();
        test2();
    }

    private static void test1() {
        Solution runner = new Solution();
        runner.gameOfLife(new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        });
    }

    private static void test2() {
        Solution runner = new Solution();
        runner.gameOfLife(new int[][]{
                {1, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0, 1},
                {0, 1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 0, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0}
        });
    }
}
