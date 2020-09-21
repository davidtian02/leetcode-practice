package com.leetcode.problems.hard.edit_distance;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/edit-distance/submissions/
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i=0; i<dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j=0; j<dp[0].length; j++) {
            dp[0][j] = j;
        }
        for (int i=1; i<dp.length; i++) {
            for (int j=1; j<dp[0].length; j++) {
                int insert = dp[i-1][j] + 1;
                int remove = dp[i][j-1] + 1;
                int replace = word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1;
                int compare = dp[i-1][j-1] + replace;
                dp[i][j] = Math.min(Math.min(insert, remove), compare);
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.minDistance("", ""));
        System.out.println(runner.minDistance("a", "b"));
        System.out.println(runner.minDistance("", "b"));
        System.out.println(runner.minDistance("horse", "ros"));
    }

    public int minDistance2(String word1, String word2) {
        Map<String, Integer> cache = new HashMap<>();
        return lev(word1, word2, 0, 0, cache);
    }

    private int lev(String word1, String word2, int i, int j, Map<String, Integer> cache) {
        String key = i+","+j;
        if (cache.containsKey(key)) return cache.get(key);
        if (i==word1.length()) return word2.length()-j;
        if (j==word2.length()) return word1.length()-i;

        final int min;
        if (word1.charAt(i) != word2.charAt(j)) {
            int replace = lev(word1, word2, i+1, j+1, cache) + 1;
            int insert = lev(word1, word2, i+1, j, cache) + 1;
            int remove = lev(word1, word2, i, j+1, cache) + 1;
            min = Math.min(Math.min(replace, insert), remove);
        } else {
            min = lev(word1, word2, i+1, j+1, cache);
        }

        cache.put(key, min);
        return min;
    }

    // note: this is literally Levenshtein's algorithm
}
