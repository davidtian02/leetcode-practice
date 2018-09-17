package com.leetcode.bestTimeToBuyAndSellStock;

// https://leetcode.com/submissions/detail/176492547/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        mCache = new int[prices.length];
        for (int i=0; i<mCache.length; i++) {
            mCache[i] = -1;
        }

        return maxProfitHelper(prices, 0);
    }

    int[] mCache;

    private int maxProfitHelper(int[] prices, int index) {
        if (index >= prices.length) {
            return 0;
        }

        if (mCache[index] != -1) {
            System.out.println("used cache");
            return mCache[index];
        }

        int startingPrice = prices[index];
        int localMax = 0;
        for (int i=index+1; i<prices.length; i++) {
            int profit = prices[i] - startingPrice;
            if (profit > localMax) {
                localMax = profit;
            }
        }

        int max = Math.max(localMax, maxProfitHelper(prices, index+1));
        mCache[index] = max;
        return max;
    }
}