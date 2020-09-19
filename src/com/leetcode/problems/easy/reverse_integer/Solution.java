package com.leetcode.problems.easy.reverse_integer;

// https://leetcode.com/submissions/detail/175588349/ beats 70% of submissions... wait really? wtf
public class Solution {
    public int reverse(int x) {
        boolean isNeg = x < 0;
        x = isNeg ? -x : x;
        String result = new StringBuilder("" + x).reverse().toString();
        try {
            return Integer.parseInt(isNeg ? ("-" + result) : result);
        } catch (Exception e) {
            return 0;
        }
    }
}
