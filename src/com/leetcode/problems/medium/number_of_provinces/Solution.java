package com.leetcode.problems.medium.number_of_provinces;

import java.util.*;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        Map<Integer, Set<Integer>> graph = initGraph(isConnected);
        int count = 0;
        while (!graph.isEmpty()) {
            int seed = graph.keySet().iterator().next();
            removeProvince(graph, seed);
            count++;
        }
        return count;
    }

    private Map<Integer, Set<Integer>> initGraph(int[][] isConnected) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i=0; i<isConnected.length; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i=0; i<isConnected.length; i++) {
            for (int j=i+1; j<isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }

    private void removeProvince(Map<Integer, Set<Integer>> graph, Integer seed) {
        Set<Integer> next = graph.remove(seed);
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(next);
        Set<Integer> visited = new HashSet<>();
        visited.add(seed);
        while (!queue.isEmpty()) {
            Integer n = queue.remove();
            next = graph.remove(n);
            if (visited.contains(n)) {
                continue;
            }
            visited.add(n);
            for (int i : next) {
                if (!visited.contains(i)) {
                    queue.add(i);
                }
            }
        }
    }

    public static void main(String...args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() {
        int[][] matrix = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        Solution runner = new Solution();
        System.out.println(runner.findCircleNum(matrix));
    }


    private static void test2() {
        int[][] matrix = new int[][]{{1,0,0},{0,1,0},{0,0,1}};
        Solution runner = new Solution();
        System.out.println(runner.findCircleNum(matrix));
    }


    private static void test3() {
        int[][] matrix = new int[][]{{1}};
        Solution runner = new Solution();
        System.out.println(runner.findCircleNum(matrix));
    }


    private static void test4() {
        int[][] matrix = new int[][]{{1,1,1},{1,1,1},{1,1,1}};
        Solution runner = new Solution();
        System.out.println(runner.findCircleNum(matrix));
    }
}
