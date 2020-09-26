package com.leetcode.challenge.september2020.largest_number;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/557/week-4-september-22nd-september-28th/3472/
class Solution {
    public String largestNumber(int[] nums) {
        if (nums.length < 1) {
            return "";
        }
        List<String> sequences = Arrays.stream(nums).mapToObj(n -> "" + n).collect(Collectors.toList());

        sequences.sort((a, b) -> {
            if (a.length() != b.length()) {
                return (a+b).compareTo(b+a);
            } else {
                return a.compareTo(b);
            }
        });

        String result = sequences.stream().reduce((temp, sum) -> sum+temp).get();
        return isZero(result) ? "0" : result;
    }

    private boolean isZero(String str) {
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
