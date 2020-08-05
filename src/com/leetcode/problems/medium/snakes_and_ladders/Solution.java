package com.leetcode.problems.medium.snakes_and_ladders;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public int snakesAndLadders(int[][] board) {
        Set<Map.Entry<Integer, Integer>> visited = new HashSet<>();
        Queue<Path> q = new LinkedList<>();
        Path start = new Path();
        start.visit(new AbstractMap.SimpleEntry<>(board.length-1, 0));
        q.add(start);
        Path p;
        while (!q.isEmpty()) {
            Path path = q.remove();
            Map.Entry<Integer, Integer> last = path.getLast();
            if (isFinished(board, last)) {
                return path.getSteps()-1;
            }
            for (int i=1; i<=6; i++) {
                Map.Entry<Integer, Integer> nextStep = getCoordinatesForStep(board, last, i);
                if (isWithinBounds(board, nextStep)) {
                    Map.Entry<Integer, Integer> translatedNextStep = translateStep(board, nextStep);
                    p = new Path(path);
                    boolean canVisit = !visited.contains(translatedNextStep);
                    if (canVisit) {
                        p.visit(translatedNextStep);
                        visited.add(translatedNextStep);
                        q.add(p);
                    }
                }
            }
        }

        return -1;
    }

    private Map.Entry<Integer, Integer> translateStep(int[][] board, Map.Entry<Integer, Integer> nextStep) {
        int x = nextStep.getKey();
        int y = nextStep.getValue();
        if (board[x][y] != -1) { // has snake or ladder, step there
            int destination = board[x][y];
            return convertDestinationToCoordinates(board, destination);
        }
        return nextStep;
    }

    private Map.Entry<Integer, Integer> convertDestinationToCoordinates(int[][] board, int destination) {
        int invertedRow = (destination-1) / board.length;
        boolean isRight = (invertedRow & 0x01) == 0;
        int col = isRight ? ((destination-1) % board.length) : (board.length-1) - ((destination-1) % board.length);
        return new AbstractMap.SimpleEntry<>((board.length-1) - invertedRow, col);
    }

    private boolean isWithinBounds(int[][] board, Map.Entry<Integer, Integer> nextStep) {
        int x = nextStep.getKey();
        int y = nextStep.getValue();
        return x >= 0 && x < board.length && y >= 0 && y < board.length;
    }

    private boolean isFinished(int[][] board, Map.Entry<Integer, Integer> step) {
        return convertCoordinateToDestination(board, step) == (board.length*board.length);
    }

    private int convertCoordinateToDestination(int[][] board, Map.Entry<Integer, Integer> coordinate) {
        int x = coordinate.getKey();
        int y = coordinate.getValue();
        int len = board.length;
        int invertedRow = len-1 - x;
        boolean isRight = (invertedRow & 0x01) == 0;
        int offset = isRight ? y : (len-1) - y;
        return 1 + (invertedRow*len) + offset;
    }

    private Map.Entry<Integer, Integer> getCoordinatesForStep(int[][] board, Map.Entry<Integer, Integer> x,int s) {
        int start = convertCoordinateToDestination(board, x);
        return convertDestinationToCoordinates(board, start + s);
    }

    private static class Path {
        Map.Entry<Integer, Integer> last;
        int steps;
        Path(Path other) {
            this.last = new AbstractMap.SimpleEntry<>(other.last.getKey(), other.last.getValue());
            this.steps = other.steps;
        }

        Path() {

        }

        void visit(Map.Entry<Integer, Integer> coordinate) {
            last = coordinate;
            steps++;
        }

        Map.Entry<Integer, Integer> getLast() {
            return last;
        }

        int getSteps() {
            return steps;
        }
    }

    public static void main(String... args) {
        int[][] board1 = new int[][]{
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        int[][] board2 = new int[][]{
            {1,1,-1},
            {1,1,1},
            {-1,1,1}
        };
        Solution runner = new Solution();
        System.out.println(runner.snakesAndLadders(board1));
        System.out.println(runner.snakesAndLadders(board2));
    }
}
