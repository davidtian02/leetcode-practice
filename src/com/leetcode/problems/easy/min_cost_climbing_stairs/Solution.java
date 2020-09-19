package com.leetcode.problems.easy.min_cost_climbing_stairs;

//https://leetcode.com/submissions/detail/179385343/
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        sCache1 = new int[cost.length];
        sCache2 = new int[cost.length];

        for (int i=0; i<sCache1.length; i++) {
            sCache1[i] = sCache2[i] = -1;
        }

        return Math.min(minCost(cost, 0), minCost(cost, 1));
    }

    int[] sCache1;
    int[] sCache2;

    public int minCost(int[] cost, int index) {
        if (index >= cost.length) {
            return 0;
        }

        int step1, step2;
        if (sCache1[index] != -1) {
            step1 = sCache1[index];
            step2 = sCache2[index];
        } else {
            step1 = cost[index] + minCost(cost, index+1);
            step2 = cost[index] + minCost(cost, index+2);

            // System.out.println("on index " + index + ", step 1: " + step1 + ", step 2: " + step2);

            sCache1[index] = step1;
            sCache2[index] = step2;
        }

        return Math.min(step1, step2);
    }
}