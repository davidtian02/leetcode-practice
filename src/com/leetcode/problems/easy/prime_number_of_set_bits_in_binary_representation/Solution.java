package com.leetcode.problems.easy.prime_number_of_set_bits_in_binary_representation;

// https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation
class Solution {
    public int countPrimeSetBits(int L, int R) {
        // 10^6 = 1000000, ~ 2^20? primes being 2, 3, 5, 7, 11, 13, 17, 19,
        int count = 0;
        for (; L<=R; L++) {
            if (isPrimeBits(L)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrimeBits(int n) {
        int count = 0;
        while(n > 0) {
            if ((n & 0x01) == 1) {
                count++;
            }
            n >>= 1;
        }

        switch (count) {
            case 2:
            case 3:
            case 5:
            case 7:
            case 11:
            case 13:
            case 17:
            case 19:
                return true;
        }
        return false;
    }
}