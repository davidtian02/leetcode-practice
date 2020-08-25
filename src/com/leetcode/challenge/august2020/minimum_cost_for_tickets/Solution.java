package com.leetcode.challenge.august2020.minimum_cost_for_tickets;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3436/
class Solution {
    private Map<Integer, Integer> cache;
    public int mincostTickets(int[] days, int[] costs) {
        cache = new HashMap<>();
        return findCheapest(days, 0, 0, costs);
    }

    private int findCheapest(int[] days, int index, int currentCost, int[] costs) {
        if (index >= days.length) {
            return 0;
        }
        if (cache.containsKey(index)) {
            return cache.get(index);
        }

        int costIfBoughtOneDay = costs[0] + findCheapest(days, index+1, currentCost, costs);
        int i2 = index;
        while (i2 < days.length && days[i2] < days[index] + 7) { // at most 7 tries
            i2++;
        }
        int costIfBoughtSevenDay = costs[1] + findCheapest(days, i2, currentCost, costs);

        int i3 = index;
        while (i3 < days.length && days[i3] < days[index] + 30) { // at most 7 tries
            i3++;
        }
        int costIfBoughtThirtyDay = costs[2] + findCheapest(days, i3, currentCost, costs);

        int minCost = Math.min(Math.min(costIfBoughtOneDay, costIfBoughtSevenDay), costIfBoughtThirtyDay);
        cache.put(index, minCost);
        return minCost;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
//        int result = runner.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15});
        int result = runner.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15});
        System.out.println(result);
    }
}
