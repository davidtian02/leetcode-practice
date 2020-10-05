package com.leetcode.problems.hard.word_search_ii;

import java.util.*;

// https://leetcode.com/explore/interview/card/microsoft/46/backtracking/256/
class Solution {
    // Runtime: O( (n*m)^4 ) ; where n is number of rows, m is number of columns in board
    // Space: O(max(n*m, words))
    public List<String> findWords(char[][] board, String[] words) {
        if (board.length < 1 || board[0].length < 1) return new LinkedList<>();
        Trie trie = constructTrie(words);

        Set<String> result = new HashSet<>();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                Set<AbstractMap.SimpleEntry<Integer, Integer>> visited = new HashSet<>();
                search(board, i, j, trie, result, visited);
            }
        }

        return new ArrayList<>(result);
    }

    private void search(char[][] board, int i, int j, Trie root, Set<String> result, Set<AbstractMap.SimpleEntry<Integer, Integer>> visited) {
        char c = board[i][j];
        AbstractMap.SimpleEntry<Integer, Integer> coordinates = new AbstractMap.SimpleEntry<>(i, j);
        if (visited.contains(coordinates)) return;
        if (!root.children.containsKey(c)) return;

        root = root.children.get(c);
        if (root.isWord) {
            result.add(constructWord(root));
        }
        visited.add(coordinates);

        // up, down, left, right
        if (i-1 >= 0) {
            search(board, i-1, j, root, result, new HashSet<>(visited));
        }

        if (i+1 < board.length) {
            search(board, i+1, j, root, result, new HashSet<>(visited));
        }

        if (j-1 >= 0) {
            search(board, i, j-1, root, result, new HashSet<>(visited));
        }

        if (j+1 < board[0].length) {
            search(board, i, j+1, root, result, new HashSet<>(visited));
        }
    }

    private String constructWord(Trie root) {
        StringBuilder sb = new StringBuilder();
        while (root.parent != null) {
            sb.append(root.me);
            root = root.parent;
        }
        return sb.reverse().toString();
    }

    private Trie constructTrie(String[] words) {
        Trie root = new Trie('$');
        for (String word : words) {
            Trie temp = root;
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (!temp.children.containsKey(c)) {
                    temp.children.put(c, new Trie(c));
                }
                Trie parent = temp;
                temp = temp.children.get(c);
                temp.parent = parent;
            }
            temp.isWord = true;
        }
        return root;
    }

    static class Trie {
        Map<Character, Trie> children = new HashMap<>();
        Trie parent;
        char me;
        boolean isWord = false;
        Trie(char c) {me = c;}
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.findWords(new char[][]{{'a'}}, new String[]{"a"}));
    }
}

/*

//     public List<String> findWords(char[][] board, String[] words) {
//         if (board == null || board.length < 1 || board[0].length < 1) {
//             return new LinkedList<>();
//         }
//         Set<String> dictionary = Arrays.stream(words).collect(Collectors.toSet());
//         Set<String> result = new HashSet<>();
//         traverse(result, board, dictionary);
//         return new ArrayList<>(result);
//     }

//     private void traverse(Set<String> result, char[][] board, Set<String> dict) {
//         for (int i=0; i<board.length; i++) {
//             for (int j=0; j<board[0].length; j++) {
//                 Set<String> visited = new HashSet<>();
//                 // System.out.println("starting out at: " + i + ", " + j);
//                 bfs(result, board, i, j, dict, visited);
//             }
//         }
//     }

//     private void bfs(Set<String> result, char[][] board, int i, int j, Set<String> dict, Set<String> visited) {
//         Queue<Path> q = new LinkedList<>();
//         Set<String> initialSet = new HashSet<>();
//         Path start = new Path(board, i, j, "" + board[i][j], initialSet);
//         q.add(start);
//         // visited.add(start.getStrCoordinates());
//         while (!q.isEmpty()) {
//             Path current = q.remove();
//             if (current.formsWord(dict)) {
//                 result.add(current.progress);
//             }
//             List<Path> nextSteps = current.nextSteps(visited);
//             for (Path step : nextSteps) {
//                 q.add(step);
//                 // visited.add(step.getStrCoordinates());
//             }
//         }
//     }

//     static class Path {
//         int row;
//         int col;
//         char[][] board;
//         String progress;
//         Set<String> visited;
//         Path(char[][] b, int i, int j, String p, Set<String> v) {
//             row = i;
//             col = j;
//             board = b;
//             progress = p;
//             visited = v;
//             // System.out.println("i and j " + i + ", " + j);
//             // System.out.println("is visited null ? " + visited);
//             // System.out.println("is coordinates null ? " + getStrCoordinates());
//             visited.add(getStrCoordinates());
//         }

//         String getStrCoordinates() {
//             return row+","+col;
//         }

//         String getStrCoordinates(int i, int j) {
//             return i+","+j;
//         }

//         List<Path> nextSteps(Set<String> v) {
//             // if in bounds and not in visited
//             List<Path> result = new LinkedList<>();
//             // up , down, left, right
//             if (row>0 && !visited.contains(getStrCoordinates(row-1, col))) {
//                 Path up = new Path(board, row-1, col, progress + board[row-1][col], new HashSet<>(visited));
//                 result.add(up);
//                 // System.out.println("up: " + up.row + ", " + up.col + ", " + up.board[up.row][up.col] + ", progress: " + up.progress);
//             }

//             if (row+1<board.length && !visited.contains(getStrCoordinates(row+1, col))) {
//                 Path down = new Path(board, row+1, col, progress + board[row+1][col], new HashSet<>(visited));
//                 result.add(down);
//                 // System.out.println("down: " + down.row + ", " + down.col + ", " + down.board[down.row][down.col] + ", progress: " + down.progress);
//             }

//             // right
//             if (col+1<board[0].length && !visited.contains(getStrCoordinates(row, col+1))) {
//                 Path right = new Path(board, row, col+1, progress + board[row][col+1], new HashSet<>(visited));
//                 result.add(right);
//                 // System.out.println("right: " + right.row + ", " + right.col + ", " + right.board[right.row][right.col] + ", progress: " + right.progress);
//             }

//             if (col-1>=0 && !visited.contains(getStrCoordinates(row, col-1))) {
//                 Path left = new Path(board, row, col-1, progress + board[row][col-1], new HashSet<>(visited));
//                 result.add(left);
//                 // System.out.println("left: " + left.row + ", " + left.col + ", " + left.board[left.row][left.col] + ", progress: " + left.progress);
//             }

//             return result;
//         }

//         boolean formsWord(Set<String> dict) {
//             return dict.contains(progress);
//         }
//     }
 */
