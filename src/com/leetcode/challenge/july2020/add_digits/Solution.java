package com.leetcode.challenge.july2020.add_digits;

// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/547/week-4-july-22nd-july-28th/3402/
public class Solution {
    public int addDigits(int num) {
        int result = 0;
        int n = num;
        while (n > 0) {
            result += n % 10;
            n /= 10;
        }
        result = (result % 10) + (result / 10); // 2 ^ 31 only has 10 digits
        return (result % 10) + (result / 10);
    }
    // 38 -> 2
    // 15 -> 6
    // 19 -> 1
    // 111 -> 3
    // 1234 -> 1
    // 1235 -> 2
    // 1236 -> 3
    // 1237 -> 4
    // 1238 -> 5
    // 1239 -> 6
    // 1240 -> 7
    // 1241 -> 8
    // 1242 -> 9
    // 1243 -> 1
    // 523541 -> 2
    //
}
