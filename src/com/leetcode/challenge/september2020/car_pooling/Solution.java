package com.leetcode.challenge.september2020.car_pooling;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/556/week-3-september-15th-september-21st/3467/
class Solution {

    public boolean carPooling(int[][] trips, int capacity) {
        int[] rollingOccupancy = new int[1001];

        for (int i=0; i<trips.length; i++) {
            int ppl = trips[i][0];
            int start = trips[i][1];
            int end = trips[i][2];
            rollingOccupancy[start] += ppl;
            rollingOccupancy[end] -= ppl;
        }

        int tempSum = 0;
        for (int i=0; i<rollingOccupancy.length; i++) {
            tempSum += rollingOccupancy[i];
            if (tempSum > capacity) {
                return false;
            }
        }

        return true;
    }
}
