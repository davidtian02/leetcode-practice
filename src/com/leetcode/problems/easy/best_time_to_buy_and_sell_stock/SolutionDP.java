package com.leetcode.problems.easy.best_time_to_buy_and_sell_stock;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/submissions/detail/176492222/  however, this runs out of time.
public class SolutionDP {
    // using DP (which runs out of time)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        return maxProfitHelper(prices, 0, prices.length - 1);
    }

    Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
    private int maxProfitHelper(int[] prices, int i, int j) {
        if (i >= prices.length || j < 0 || i >= j) {
            return 0;
        }

        Pair<Integer, Integer> pair = new Pair<>(i, j);

        if (cache.containsKey(pair)) {
            return cache.get(pair);
        }

        int difference = prices[j] - prices[i];

        int max = Math.max( maxProfitHelper(prices, i, j-1), maxProfitHelper(prices, i+1, j) );
        if (max < difference) {
            max = difference;
        }

        cache.put(pair, max);
        return max;
    }
}
