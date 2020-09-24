package com.leetcode.challenge.september2020.gas_station;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/557/week-4-september-22nd-september-28th/3470/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startCandidate = -1;
        for (int i=0; i<gas.length;) {
            if (gas[i] >= cost[i]) { // can start here
                startCandidate = i;
                int g = gas[i];
                int remainingGas = g - cost[i]; // traverse the first stop
                int len = 1;
                int j = i+1;
                while (remainingGas >= 0) {
                    if (j == gas.length) {
                        j = 0; // loop around
                    }
                    if (len == gas.length) {
                        // found solution
                        return startCandidate;
                    }
                    remainingGas += gas[j] - cost[j];
                    j++;
                    len++;
                }
                if (len < gas.length || cost[j] > remainingGas) {
                    // cuz need to make full circle, including self
                    startCandidate = -1;
                }
                i += len;
            } else {
                i++;
            }
        }
        return startCandidate;
    }
}
