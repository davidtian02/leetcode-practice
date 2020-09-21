package com.leetcode.problems.hard.longest_consecutive_sequence;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/longest-consecutive-sequence/
class Solution {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int max = 0;
        while (!set.isEmpty()) {
            Integer seed = set.iterator().next();
            int count=0, left=seed-1, right=seed;
            while (set.remove(left--)) count++;
            while (set.remove(right++)) count++;
            max = Math.max(count, max);
        }
        return max;
    }

    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> freq = makeFrequency(nums);
        int max = 0;
        while (!freq.isEmpty()) {
            Iterator<Map.Entry<Integer, Integer>> iter = freq.entrySet().iterator();
            Map.Entry<Integer, Integer> seed = iter.next();
            // so go left and right...
            freq.remove(seed.getKey());
            int left = goLeft(freq, seed.getKey()-1);
            int right = goRight(freq, seed.getKey()+1);
            int count = 1 + left + right;
            max = Math.max(count, max);
        }
        return max;
    }

    private int goLeft(Map<Integer, Integer> freq, int l) {
        int max = 0;
        while (freq.containsKey(l)) {
            max++;
            freq.remove(l);
            l--;
        }
        return max;
    }

    private int goRight(Map<Integer, Integer> freq, int r) {
        int max = 0;
        while (freq.containsKey(r)) {
            max++;
            freq.remove(r);
            r++;
        }

        return max;
    }

    private Map<Integer, Integer> makeFrequency(int nums[]) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            result.put(nums[i], result.containsKey(nums[i])?result.get(nums[i])+1:1);
        }
        return result;
    }
}

/**
 [1,1,1,2,3,4]
 [1,2,3,3,3,3,3,4]
 [1,2,3,3,3,3,3,4,4,4,4,5,6,7,8,9,10,11,11,12,13,21,22,23,100]
 */
