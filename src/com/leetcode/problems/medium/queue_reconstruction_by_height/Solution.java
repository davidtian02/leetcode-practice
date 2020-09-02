package com.leetcode.problems.medium.queue_reconstruction_by_height;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO - this prolly has a more optimal solution via a BST
// https://leetcode.com/problems/queue-reconstruction-by-height/submissions/
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) return people;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int[] pp : people) {
            pq.add(new Pair(pp));
        }
        List<Pair> sorted = new LinkedList<>();
        while (!pq.isEmpty()) {
            Pair next = pq.remove();
            sorted.add(next.k, next);
        }
        return sorted.stream().map(p -> new int[]{p.h, p.k}).collect(Collectors.toList()).toArray(new int[][]{new int[]{}});
    }

    static class Pair implements Comparable<Pair> {
        int h, k;
        Pair(int[] hk) {
            h = hk[0];
            k = hk[1];
        }
        @Override
        public int compareTo(Pair other) {
            if (h < other.h) {
                return 1;
            } else if (h > other.h) {
                return -1;
            } else {
                return Integer.compare(k, other.k);
            }
        }
    }
}