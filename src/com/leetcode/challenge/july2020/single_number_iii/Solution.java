package com.leetcode.challenge.july2020.single_number_iii;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] singleNumber(int[] nums) {
        int xorCombined = 0;
        for (int n : nums) {
            xorCombined ^= n;
        }
        // find differing bit. result can't be 0, cuz can't have 0 zeros
        int differentiator = 1;
        while ((xorCombined & 0x01) == 0) {
            xorCombined >>= 1;
            differentiator <<= 1;
        }
        int oneAtDigit = 0;
        int zeroAtDigit = 0;
        for (int n : nums) {
            if ((n & differentiator) != 0) {
                oneAtDigit ^= n;
            } else {
                zeroAtDigit ^= n;
            }
        }
        return new int[]{oneAtDigit, zeroAtDigit};
    }

    private int[] backupway(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }
        Object[] o = set.toArray();
        return new int[]{(int)o[0], (int)o[1]};
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(Arrays.toString(runner.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }
}
