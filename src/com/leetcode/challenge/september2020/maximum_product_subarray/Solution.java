package com.leetcode.challenge.september2020.maximum_product_subarray;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/555/week-2-september-8th-september-14th/3456/
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length < 2) { // can't have none
            return nums[0];
        }

        int maxProd=nums[0];
        int minProd=nums[0];
        int max = nums[0];
        int prevMaxProd;
        for (int i=1; i<nums.length; i++) {
            prevMaxProd = maxProd;
            maxProd = Math.max(maxProd*nums[i], nums[i]);
            max = Math.max(maxProd, max);
            max = Math.max(nums[i]*minProd, max);
            maxProd = Math.max(minProd*nums[i], maxProd);
            minProd = Math.min(minProd*nums[i], nums[i]);
            minProd = Math.min(minProd, nums[i]*prevMaxProd);
        }

        return max;
    }
    // section off by 0's
    public int maxProduct2(int[] nums) {
        if (nums.length < 2) { // can't have none
            return nums[0];
        }

        int begin = 0;
        int max = nums[0];
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == 0) {
                int localMax = Math.max(0, findMax(nums, begin, i-1));
                max = Math.max(max, localMax);
                begin = i+1;
            }
        }

        return Math.max(max, findMax(nums, begin, nums.length-1));
    }

    private int findMax(int[] nums, int begin, int end) { // i guess both inclusive
        if (end < 0) {
            return nums[begin];
        }
        if (begin >= end) { // no longer includes 0s
            return nums[end];
        }

        int max;
        int numNegs = 0;
        int product = 1;
        for (int i=begin; i<=end; i++) {
            if (nums[i] < 0) {
                numNegs++;
            }
            product *= nums[i];
        }

        if ((numNegs & 0x01) == 0) {
            return product;
        } else {
            // odd number of negatives hmm...
            Integer indexOfFirstNeg=null, indexOfLastNeg=null;
            for (int i=begin; i<=end; i++) {
                if (nums[i] < 0 && indexOfFirstNeg == null) {
                    indexOfFirstNeg = i;
                    indexOfLastNeg = i;
                }
                if (nums[i] < 0 && i > indexOfLastNeg) {
                    indexOfLastNeg = i;
                }
            }

            // TODO is it possible to only have 1 neg?
            // product until lastNeg, but not inclusive of it:
            int p1 = 1, p2 = 1;
            for (int i=begin; i<indexOfLastNeg; i++) {
                p1 *= nums[i];
            }

            // product from firstNeg to end, but not inclusive of it:
            for (int i=indexOfFirstNeg+1; i<=end; i++) {
                p2 *= nums[i];
            }

            return Math.max(p1, p2);
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.maxProduct(new int[]{0,2}));
        System.out.println(runner.maxProduct(new int[]{0,0}));
        System.out.println(runner.maxProduct(new int[]{0,0,0,0}));
        System.out.println(runner.maxProduct(new int[]{-2,0}));
        System.out.println(runner.maxProduct(new int[]{0}));
        System.out.println(runner.maxProduct(new int[]{-2,0,1}));
        System.out.println(runner.maxProduct(new int[]{2,3,-2,4}));
        System.out.println(runner.maxProduct(new int[]{2,3,5,2,1,0,-4}));
        System.out.println(runner.maxProduct(new int[]{2,3,5,2,1,0,0}));
        System.out.println(runner.maxProduct(new int[]{2,3,5,2,1,0,0,0,5,5,5,5,5,5,5}));
        System.out.println(runner.maxProduct(new int[]{-2,3,5,0,0,0,1,2,-4}));
        System.out.println(runner.maxProduct(new int[]{5,0,-2,3,-5,2,0,0,0,1,2,-4}));
        System.out.println(runner.maxProduct(new int[]{2,-3,5,-2,1,0,0,0,-4,5,-1,3,-2,2,-6}));
    }
}
