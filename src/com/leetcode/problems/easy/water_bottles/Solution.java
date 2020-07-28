package com.leetcode.problems.easy.water_bottles;

// https://leetcode.com/problems/water-bottles/
public class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int previousRemainder = 0;
        int full = numBottles;
        int total = 0;
        int empty = 0;
        while (full > 0) {
            total += full;
            empty = full + previousRemainder;
            previousRemainder = empty % numExchange;
            full = empty / numExchange;
        }
        return total;
    }
}
