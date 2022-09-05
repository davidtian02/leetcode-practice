package com.leetcode.problems.easy.happy_number;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> checked = new HashSet<>();
        while (n != 1) {
            int temp = n;
            int rollingSum = 0;
            while (temp > 0) {
                int digit = temp % 10;
                rollingSum += digit*digit;
                temp /= 10;
            }
            n = rollingSum;
            if (checked.contains(n)) {
                return false;
            }
            checked.add(n);
        }
        return true;
    }
}
