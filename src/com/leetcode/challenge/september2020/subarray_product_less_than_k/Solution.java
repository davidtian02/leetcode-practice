package com.leetcode.challenge.september2020.subarray_product_less_than_k;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/557/week-4-september-22nd-september-28th/3475/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // we can use sliding window.
        // as it turns out, n* (n+1) / 2 is actually the sum of all. BUT adding 1 more to each prior set means +1 to each "groups" of sets. so then just +n

        int dequeSize = 0;
        int total = 0;
        int i=0, j=i;
        long product = 1;
        while (j<nums.length) {
            if (product * nums[j] < k) {
                // then add to deque
                product *= nums[j];
                dequeSize++;
                total += dequeSize;
                j++;
            } else {
                if (dequeSize == 0) {
                    j++;
                    i = j;
                    product = 1;
                } else {
                    product /= nums[i]; // remove from front
                    dequeSize--;
                    i++;
                }
            }
        }
        return total;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.numSubarrayProductLessThanK(new int[]{1,2,3}, 0));
        System.out.println(runner.numSubarrayProductLessThanK(new int[]{10, 5, 15}, 3));
        System.out.println(runner.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        System.out.println(runner.numSubarrayProductLessThanK(new int[]{1,1,1,1,1,1}, 100));
    }
}
