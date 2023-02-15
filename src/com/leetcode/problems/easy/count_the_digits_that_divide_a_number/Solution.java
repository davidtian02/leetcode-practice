package com.leetcode.problems.easy.count_the_digits_that_divide_a_number;

class Solution {
    public int countDigits(int num) {
        int[] digits = buildDigits(num);
        return countDigits(digits);
    }

    private int[] buildDigits(int num) {
        int[] digits = new int[10];
        int i;
        int n = num;
        while (n > 0) {
            i = n % 10;
            if (num % i == 0) {
                digits[i]++;
            }
            n /= 10;
        }
        return digits;
    }

    private int countDigits(int[] digits) {
        int count = 0;
        for (int i=0; i<digits.length; i++) {
            count += digits[i];
        }
        return count;
    }
}
