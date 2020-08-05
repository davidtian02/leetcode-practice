package com.leetcode.challenge.august2020.power_of_four;

// https://leetcode.com/explore/featured/card/august-leetcoding-challenge/549/week-1-august-1st-august-7th/3412/
public class Solution {
    public boolean isPowerOfFour(int num) {
        switch (num) {
            case 1:
            case (1 << 2):
            case (1 << 4):
            case (1 << 6):
            case (1 << 8):
            case (1 << 10):
            case (1 << 12):
            case (1 << 14):
            case (1 << 16):
            case (1 << 18):
            case (1 << 20):
            case (1 << 22):
            case (1 << 24):
            case (1 << 26):
            case (1 << 28):
            case (1 << 30):
                return true;
        }
        return false;
    }
}
