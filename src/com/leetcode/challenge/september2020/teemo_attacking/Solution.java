package com.leetcode.challenge.september2020.teemo_attacking;

class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int totalTime = 0;
        if (timeSeries.length < 1) {
            return totalTime;
        }
        for (int i=1; i<timeSeries.length; i++) {
            if (timeSeries[i-1] + duration >= timeSeries[i]) {
                totalTime += timeSeries[i] - timeSeries[i-1];
            } else {
                totalTime += duration;
            }
        }
        totalTime += duration;
        return totalTime;
    }
}
