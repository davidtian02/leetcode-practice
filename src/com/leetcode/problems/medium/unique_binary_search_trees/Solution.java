package com.leetcode.problems.medium.unique_binary_search_trees;

// https://leetcode.com/problems/unique-binary-search-trees/
class Solution {
    // after drawing this out, you ahve a weird math formula with pics.
    // you'll realize that f(3) = f(2)*f(0) + f(1)*f(1) + f(0)*f(2)...
    public int numTrees(int n) {
        int dp[] = new int[n+1];
        dp[0] = 1;
        for (int i=1; i<=n; i++) {
            int sum=0, mid=i>>1;
            for (int j=0; j<mid; j++) {
                sum += dp[i-1-j] * dp[j];
            }
            dp[i] = (sum<<1) + (((i&0x01)==1)?dp[mid]*dp[mid]:0);
        }

        return dp[n];
    }
}
