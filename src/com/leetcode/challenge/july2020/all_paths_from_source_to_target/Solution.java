package com.leetcode.challenge.july2020.all_paths_from_source_to_target;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/547/week-4-july-22nd-july-28th/3400/
class Solution {
    // add back to the chain. need a queue of chains
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Queue<List<Integer>> allChains = new LinkedList<>();
        List<Integer> chain;
        List<List<Integer>> result = new LinkedList<>();
        for (int a : graph[0]) {
            chain = new ArrayList<>();
            chain.add(0);
            chain.add(a);
            if (a == graph.length - 1) {
                result.add(chain);
            } else {
                allChains.add(chain);
            }
        }
        while (!allChains.isEmpty()) {
            chain = allChains.remove();
            int last = chain.get(chain.size()-1);
            for (int a : graph[last]) { // oops...
                ArrayList<Integer> temp = new ArrayList<>(chain);
                temp.add(a);
                if (a == graph.length - 1) {
                    // found last node
                    result.add(temp);
                } else {
                    allChains.add(temp);
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        List<List<Integer>> lists = runner.allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}});
        System.out.println(lists);
    }
}
