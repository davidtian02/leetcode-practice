package com.leetcode.problems.easy.distribute_candies;

import java.util.Arrays;

class Solution {
    // Big O runtime:
    // space complexity:
    public int distributeCandies(int[] candies) {
        int sister = (int) Arrays.stream(candies).distinct().count();
        int brother = candies.length - sister;
        if (brother > sister) {
            return sister; // since brother has extra
        } else {
            return ((sister - brother) >> 1) + brother; // average the two
        }
    }


    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.distributeCandies(new int[]{1,1,2,3}));
    }
}
