package com.leetcode.challenge.august2020.numbers_with_same_consecutive_differences;

import java.util.*;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/551/week-3-august-15th-august-21st/3428/
class Solution {
    public int[] numsSameConsecDiff(int N, int K) {
        StringBuilder temp = new StringBuilder();
        Set<String> result = new HashSet<>();

        if (N == 1) {
            return new int[]{0,1,2,3,4,5,6,7,8,9};
        }

        for (int i = 0; i < 10; i++) {
            generateAll(temp, i, N, K, result);
        }

        return result.stream().mapToInt(Integer::parseInt).toArray();
    }

    private void generateAll(StringBuilder temp, int prev, int n, int k, Set<String> result) {
        if (n == 0) {
            if (temp.charAt(0) != '0') {
                result.add(temp.toString());
            }
            return;
        }
        int aPlus = -1;
        int aMinus = -1;
        int a = prev;

        if (a + k < 10) {
            aPlus = a + k;
            generateAll(new StringBuilder(temp).append(aPlus), aPlus, n - 1, k, result);
        }

        if (a - k >= 0) {
            aMinus = a - k;
            if (aMinus != aPlus) { // cuz would double generate, if say... k was 0
                generateAll(new StringBuilder(temp).append(aMinus), aMinus, n - 1, k, result);
            }
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
//        System.out.println(Arrays.toString(runner.numsSameConsecDiff(3, 7)));
//        System.out.println(Arrays.toString(runner.numsSameConsecDiff(3, 1)));
        System.out.println(Arrays.toString(runner.numsSameConsecDiff(1, 0)));
        System.out.println(Arrays.toString(runner.numsSameConsecDiff(1, 6)));
    }
}