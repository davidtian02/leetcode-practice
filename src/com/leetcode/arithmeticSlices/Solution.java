package com.leetcode.arithmeticSlices;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int[] differences = new int[A.length - 1];

        for (int i=1; i<A.length; i++) {
            differences[i-1] = A[i] - A[i-1];
        }

        int temp = 1;
        List<Integer> duplicateCounts = new LinkedList<>();
        for (int i=1; i<differences.length; i++) {
            if (differences[i] == differences[i-1]) {
                temp++;
            } else {
                if (temp > 1) {
                    duplicateCounts.add(temp);
                }
                temp = 1;
            }
        }

        if (temp > 1) {
            duplicateCounts.add(temp);
        }

        System.out.println(duplicateCounts);

        int sum = 0;
        for (Integer d : duplicateCounts) {
            sum += findAllConsecutiveCount(d);
        }

        return sum;
    }

    Map<Integer, Integer> cache = new HashMap<>();
    private int findAllConsecutiveCount(int n) {
        if (n < 2) {
            return 0;
        } else if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int result = findAllConsecutiveCount(n-1) + n - 1;
        cache.put(n, result);

        return result;
    }
}