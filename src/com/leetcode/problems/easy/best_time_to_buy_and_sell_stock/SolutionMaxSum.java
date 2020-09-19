package com.leetcode.problems.easy.best_time_to_buy_and_sell_stock;

class SolutionMaxSum {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[] diffs = new int[prices.length - 1];
        for (int i=0; i<prices.length-1; i++) {
            diffs[i] = prices[i+1] - prices[i];
        }

        int temp=0, max = 0;
        int start=-1, end=-1;
        int tempStart=0;
        for (int i=0; i<diffs.length; i++) {
            if (diffs[i] + temp > 0) {
                temp += diffs[i];
            } else {
                temp = 0;
            }

            if (temp > max) {
                max = temp;
                start = tempStart;
                end = i;
            }

            if (temp == 0) {
                tempStart = i+1;
            }
        }

        System.out.println("start, end: " + start + ", " + end);
        System.out.println("max: " + max);

        if (end != -1) {
            max = prices[end+1] - prices[start];
        }

        return max;
    }
}