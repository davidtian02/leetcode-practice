package com.leetcode.challenge.july2020;

public class ArrangingCoins {

    // https://leetcode.com/explore/featured/card/july-leetcoding-challenge/544/week-1-july-1st-july-7th/3377/
    // submission: https://leetcode.com/submissions/detail/364609229/?from=/explore/featured/card/july-leetcoding-challenge/544/week-1-july-1st-july-7th/3377/
    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.arrangeCoins(5)); // 2
        System.out.println(runner.arrangeCoins(1)); // 1
        System.out.println(runner.arrangeCoins(2)); // 1
        System.out.println(runner.arrangeCoins(0)); // 0
        System.out.println(runner.arrangeCoins(4)); // 2
        System.out.println(runner.arrangeCoins(3)); // 2
        System.out.println(runner.arrangeCoins(8)); // 3
        System.out.println(runner.arrangeCoins(6)); // 6
        System.out.println(runner.arrangeCoins(100000)); // 446
        System.out.println(runner.arrangeCoins(10000000)); // 4471
        System.out.println(runner.arrangeCoins(1000000000)); // 44720
    }

    // TODO optimize for hardcoding first 100?
    static class Solution {
        public int arrangeCoins(int target) {
            if (target == 0) {
                return 0; // ?
            }

            int index = 1;
            long sum = 1;
            // first reach highest limit
            while (target > sum) {
                index <<= 1;
                sum = calcStairCount(index);
            }
            if (sum == target) {
                return index;
            }

            // then reach inbetween
            return binarySearch(index>>1, index, target);
        }

        private int binarySearch(int left, int right, int target) {
            if (left >= right) {
                return left;
            }

            int mid = ((right - left) >> 1) + left;
            long sum = calcStairCount(mid);
            if (target > sum) {
                if (target < calcStairCount(mid+1)) {
                    return mid;
                }
                return binarySearch(mid, right, target);
            } else {
                if (target == sum) {
                    return mid;
                }
                return binarySearch(left, mid, target);
            }
        }

        private long calcStairCount(long n) {
            if ((n & 0x01) == 1) {
                return ((n + 1) * (n >> 1)) + (n >> 1) + 1;
            } else {
                return (1 + n) * (n >> 1);
            }
        }
    }
}