package com.leetcode.problems.medium.minesweeper;

//https://leetcode.com/problems/minesweeper/description/
import java.awt.Point;
import java.util.*;

class Solution {
    // 4:35

    public char[][] updateBoard(char[][] board, int[] click) {
        int i=click[0], j=click[1];
        // check game over
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }

        // position has to be 'E' now

        // has mine next to it
        int mineCount = countMines(board, i, j);
        if (mineCount > 0) {
            board[i][j] = (char)('0' + mineCount);
            return board;
        }

        // update with Bs and numbers
        board[i][j] = 'B';
        revealEmpty(board, i, j);

        return board;
    }

    List<Point> moves = Arrays.asList(
            new Point(-1, -1),
            new Point(-1, 0),
            new Point(-1, 1),
            new Point(0, -1),
            new Point(0, 1),
            new Point(1, -1),
            new Point(1, 0),
            new Point(1, 1)
    );

    private void revealEmpty(char[][] board, int i, int j) {
        Set<Point> visited = new HashSet<>();
        Point p = new Point(i, j);
        visited.add(p);
        Queue<Point> q = new LinkedList<>();
        q.offer(p);
        List<Point> children;
        int count;
        while(!q.isEmpty()) {
            p = q.poll();
            children = findValidMoves(board, p);
            for (Point c : children) {
                if (!visited.contains(c)) {
                    count = countMines(board, c.x, c.y);
                    if (count > 0) {
                        board[c.x][c.y] = (char)('0' + count);
                    } else {
                        board[c.x][c.y] = 'B';
                        q.offer(c);
                    }
                    visited.add(c);
                }
            }
        }
    }

    private List<Point> findValidMoves(char[][] board, Point p) {
        List<Point> result = new LinkedList<>();
        Point temp;
        for (Point m : moves) {
            temp = move(p, m);
            if (withinBounds(temp, board) && board[temp.x][temp.y] == 'E') {
                result.add(temp);
            }
        }
        return result;
    }

    private int countMines(char[][] board, int i, int j) {
        Point p = new Point(i, j);
        Point temp;
        int count = 0;
        for (Point m : moves) {
            temp = move(p, m);
            if (withinBounds(temp, board) && board[temp.x][temp.y] == 'M') {
                count++;
            }
        }
        return count;
    }

    private boolean withinBounds(Point p, char[][] board) {
        return (p.x >= 0 && p.x < board.length && p.y >= 0 && p.y < board[0].length);
    }

    Point move(Point p1, Point p2) {
        return new Point(p1.x+p2.x, p1.y+p2.y);
    }
}