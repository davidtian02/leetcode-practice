package com.leetcode.problems.easy.climbing_stairs;

import java.util.Arrays;

class Solution {

    public int climbStairs(int n) {
        if (n <= 0) {
            return 0; // invalid
        }
        mCache = new int[n];
        Arrays.fill(mCache, -1);
        return steps(n, 0);
    }

    private int[] mCache;
    private int steps(int target, int currentStep) {
        if (currentStep > target) {
            return 0;
        } else if (currentStep == target) {
            return 1;
        } else {
            int oneStep, twoStep, sum;
            if (mCache[currentStep] != -1) {
                return mCache[currentStep];
            }
            oneStep = steps(target, currentStep + 1);
            twoStep = steps(target, currentStep + 2);
            sum = oneStep + twoStep;
            mCache[currentStep] = sum;
            return sum;
        }
    }
}