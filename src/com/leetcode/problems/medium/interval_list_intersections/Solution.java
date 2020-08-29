package com.leetcode.problems.medium.interval_list_intersections;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/interval-list-intersections/submissions/
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        // need 2 indices
        int ia=0, ib=0;
        List<Pair<Integer, Integer>> result = new LinkedList<>();

        while(ia < A.length && ib < B.length) { // merge...
            if (canMerge(A[ia], B[ib])) {
                merge(A[ia], B[ib], result);
            }
            Pair<Integer, Integer> nextIndicies = advance(A, B, ia, ib); // advance the one with the shorter ending
            ia = nextIndicies.getKey();
            ib = nextIndicies.getValue();
        }

        return mapResult(result);
    }

    private boolean canMerge(int[] a, int[] b) {
        return a[0] >= b[0] ? a[0] <= b[1] : b[0] <= a[1];
    }

    private void merge(int[] a, int[] b, List<Pair<Integer, Integer>> result) {
        // pick the largest x, smallest y
        result.add(new Pair<>(Math.max(a[0], b[0]), Math.min(a[1], b[1])));
    }

    private Pair<Integer, Integer> advance(int[][] a, int[][] b, int ia, int ib) {
        int[] top = a[ia];
        int[] bottom = b[ib];

        // reached end
        if (ia == a.length - 1) {
            return new Pair<>(ia, ib+1);
        } else if (ib == b.length - 1) {
            return new Pair<>(ia+1, ib);
        }

        if (top[1] < bottom[1]) {
            return new Pair<>(ia+1, ib);
        } else {
            return new Pair<>(ia, ib+1);
        }
    }

    private int[][] mapResult(List<Pair<Integer, Integer>> result) {
        return result.stream().map(p -> new int[]{p.getKey(), p.getValue()}).toArray(int[][]::new);
    }

    static class Pair<S, T> {
        S key;
        T val;
        Pair(S a, T b) {
            key = a;
            val = b;
        }
        S getKey() {
            return key;
        }
        T getValue() {
            return val;
        }
    }
}