package com.leetcode.problems.easy.alternating_digit_sum;

class Solution {
    public int alternateDigitSum(int n) {
        int sign = 1;

        int sum = 0;
        while (n > 0) {
            int d = (n % 10) * sign;
            sum += d;
            sign = -sign;
            n /= 10;
        }

        return -sign * sum;
    }
}
