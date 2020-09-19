package com.leetcode.challenge.september2020.best_time_to_buy_and_sell_stock;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/556/week-3-september-15th-september-21st/3464/
class Solution {
    public int maxProfit(int[] prices) {
        int maxDelta = 0;
        if (prices.length < 2) return maxDelta;
        int localMin = prices[0];
        for (int i=1; i<prices.length; i++) {
            int p = prices[i];
            if (p < localMin) { // buy
                localMin = p;
            } else { // check selling price
                maxDelta = Math.max(p - localMin, maxDelta);
            }
        }

        return maxDelta;
    }
}

// mental test cases:
// [], [1] -> 0
// [1, 2] -> 1
// [3,2,1] -> 0
// [3,2,6,9,1,5] -> 7
// [1,4,0,3] -> 3
