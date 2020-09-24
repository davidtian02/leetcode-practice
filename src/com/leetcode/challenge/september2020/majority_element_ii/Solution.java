package com.leetcode.challenge.september2020.majority_element_ii;

import java.util.*;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/557/week-4-september-22nd-september-28th/3469/
public class Solution {

    List<Integer> majorityElement(int[] nums) {
        Integer candidateA=null, candidateB=null;
        int countA=0, countB=0;
        for (int n : nums) {
            if (candidateA != null && candidateA == n) {
                countA++;
            } else if (candidateB != null && candidateB == n) {
                countB++;
            } else if (candidateA == null || candidateB == null) {
                if (candidateA == null) {
                    candidateA = n;
                    countA = 1;
                } else {
                    candidateB = n;
                    countB = 1;
                }
            } else {
                countA--;
                countB--;
                if (countA == 0) candidateA = null;
                if (countB == 0) candidateB = null;
            }
        }

        Set<Integer> result = new HashSet<>();
        if (verifyMajority(nums, candidateA)) {
            result.add(candidateA);
        }

        if (verifyMajority(nums, candidateB)) {
            result.add(candidateB);
        }

        return new ArrayList<>(result);
    }

    private boolean verifyMajority(int[] nums, Integer candidate) {
        if (candidate == null) return false;
        int freq = 0;
        for (int n : nums) freq += n == candidate ? 1 : 0;
        return freq > nums.length/3;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.majorityElement(new int[]{1,3,3,4}));
        System.out.println(runner.majorityElement(new int[]{6,6,6,7,7}));
//        System.out.println(runner.majorityElement(new int[]{1,2,3}));
//        System.out.println(runner.majorityElement(new int[]{3,2,3}));
//        System.out.println(runner.majorityElement(new int[]{1,2,3,2,4,2,0,1,2,0,3,5}));
    }
}
