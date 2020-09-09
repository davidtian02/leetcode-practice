package com.leetcode.challenge.september2020.compare_version_numbers;

import java.util.Arrays;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/555/week-2-september-8th-september-14th/3454/
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] vs1 = version1.split("\\.");
        String[] vs2 = version2.split("\\.");
        int[] v1 = Arrays.stream(vs1).mapToInt(Integer::parseInt).toArray();
        int[] v2 = Arrays.stream(vs2).mapToInt(Integer::parseInt).toArray();
        int lenShorter = v1.length < v2.length ? v1.length : v2.length;
        int lenLonger = v1.length < v2.length ? v2.length : v1.length;
        for (int i=0; i<lenShorter; i++) {
            if (v1[i] < v2[i]) {
                return -1;
            } else if (v1[i] > v2[i]) {
                return 1;
            }
        }
        if (lenShorter != lenLonger) {
            int[] vLonger = v1.length < v2.length ? v2 : v1;
            for (int i=lenShorter; i<lenLonger; i++) {
                if (vLonger[i] > 0) {
                    return v1.length < v2.length ? -1 : 1;
                }
            }
        }

        return 0;
    }
}
