package com.leetcode.challenge.july2020.word_search;

import java.util.*;

// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/546/week-3-july-15th-july-21st/3397/
class Solution {
    public boolean exist(char[][] board, String word) {
        char seed = word.charAt(0);
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == seed) {
                    if (checkWordDfs(board, i, j, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkWordDfs(char[][] board, int i, int j, String word) {
        Path root = new Path();
        root.visit(i, j);
        return dfsHelper(board, root, word);
    }

    private boolean dfsHelper(char[][] board, Path current, String word) {
        if (current.indexOfWord == word.length()) {
            return true;
        }
        char c = word.charAt(current.indexOfWord);
        List<Path> paths = checkAroundNode(board, current, c);
        for (Path p : paths) {
            if (dfsHelper(board, p, word)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkWordBFS(char[][] board, int i, int j, String word) {
        Queue<Path> paths = new LinkedList<>();
        Path root = new Path();
        root.visit(i, j);
        paths.add(root);

        while (!paths.isEmpty()) {
            Path current = paths.remove();
            if (current.indexOfWord == word.length()) {
                return true;
            }
            char c = word.charAt(current.indexOfWord);
            List<Path> nextPaths = checkAroundNode(board, current, c);
            if (!nextPaths.isEmpty()) {

                paths.addAll(nextPaths);
            }
        }

        return false;
    }

    private List<Path> checkAroundNode(char[][] board, Path p, char c) {
        Map.Entry<Integer, Integer> up = new AbstractMap.SimpleEntry<>(p.coordinates.getKey(), p.coordinates.getValue() - 1);
        Map.Entry<Integer, Integer> down = new AbstractMap.SimpleEntry<>(p.coordinates.getKey(), p.coordinates.getValue() + 1);
        Map.Entry<Integer, Integer> left = new AbstractMap.SimpleEntry<>(p.coordinates.getKey() - 1, p.coordinates.getValue());
        Map.Entry<Integer, Integer> right = new AbstractMap.SimpleEntry<>(p.coordinates.getKey() + 1, p.coordinates.getValue());
        List<Map.Entry<Integer, Integer>> arr = Arrays.asList(up, down, left, right);
        List<Path> result = new LinkedList<>();
        for (Map.Entry<Integer, Integer> a : arr) {
            if (a.getKey() >= 0 && a.getKey() < board.length
                    && a.getValue() >= 0 && a.getValue() < board[0].length
                    && !p.hasVisited(a.getKey(), a.getValue()) && c == board[a.getKey()][a.getValue()]) {
                Path n = new Path(p);
                n.visit(a.getKey(), a.getValue());
                result.add(n);
            }
        }
        return result;
    }

    private static class Path {
        Map.Entry<Integer, Integer> coordinates;
        Set<Map.Entry<Integer, Integer>> visited;
        int indexOfWord;
        Path() {
            visited = new HashSet<>();
            indexOfWord = 0;
        }
        Path(Path other) {
            this.indexOfWord = other.indexOfWord;
            this.visited = new HashSet<>(other.visited);
        }
        boolean hasVisited(int x, int y) {
            return visited.contains(new AbstractMap.SimpleEntry<>(x, y));
        }
        void visit(int x, int y) {
            coordinates = new AbstractMap.SimpleEntry<>(x, y);
            visited.add(coordinates);
            indexOfWord++;
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        System.out.println(runner.exist(board, "ABCCED"));
//
//        char[][] board2 = {
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        };
//        System.out.println(runner.exist(board2, "ABCB"));
//
//        char[][] board3 = {
//                {'C','A','A'},
//                {'A','A','A'},
//                {'B','C','D'}
//        };
//        System.out.println(runner.exist(board3, "AAB"));
//
//        char[][] board4 = {
//                {'A','B','C','E'},
//                {'S','F','E','S'},
//                {'A','D','E','E'}
//        };
//        System.out.println(runner.exist(board4, "ABCESEEEFS"));
//
//        char[][] board5 = {
//                {'A'},
//        };
//        System.out.println(runner.exist(board5, "A"));

        char[][] board6 = {
                {'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},
                {'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','b'}
        };
        System.out.println(runner.exist(board6, "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }
}
