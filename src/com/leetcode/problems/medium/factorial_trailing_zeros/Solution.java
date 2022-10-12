package com.leetcode.problems.medium.factorial_trailing_zeros;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int trailingZeroes(int n) {
        int largestFive = findLargest(n);
        int i=0, count=0;
        while (i <= largestFive) {
            i++;
            int base = (int) Math.pow(5, i); // 5, 25, 125...
            int canFit = n / base;
            count += canFit;
        }
        return count;
    }

    public int trailingZeroes2(int n) {
        int largestFive = findLargest(n);
        Map<Integer, Integer> countsOfFive = new HashMap<>();

        while (largestFive > 0) {
            int base = (int) Math.pow(5, largestFive); // in here, it's less than or eq 3125. wont overflow
            // System.out.println("starting temp: " + base);
            int temp = base;
            while (temp <= n) {
                // System.out.println("temp: " + temp);
                if (!countsOfFive.containsKey(temp)) {
                    countsOfFive.put(temp, largestFive);
                }
                temp += base;
            }
            largestFive--;
        }

        // System.out.println(countsOfFive);

        return sumCounts(countsOfFive);
    }

    private int sumCounts(Map<Integer, Integer> map) {
        int n = 0;
        for (Integer v : map.values()) {
            n += v;
        }
        return n;
    }

    private int findLargest(int n) {
        int i = 0;
        int t = 5;
        while (n >= t) {
            t *= 5; // log
            i++;
        }
        return i;
    }
}
