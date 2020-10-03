package com.leetcode.challenge.october2020.k_diff_pairs_in_an_array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3482/
class Solution {
    // Runtime: O(N), where n is number of elements in nums[]
    // Space: O(N), where n is number of elements in nums[]
    public int findPairs(int[] nums, int k) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int count = 0;

        if (k==0) {
            return countDupes(nums);
        }

        while (!set.isEmpty()) {
            int seed = set.iterator().next();
            count += deleteChain(seed+k, set, k);
            count += deleteChain(seed-k, set, -k);
            set.remove(seed);
        }
        return count;
    }

    private int deleteChain(int seed, Set<Integer> set, int delta) {
        int count = 0;
        while (!set.isEmpty() && set.contains(seed)) {
            count++;
            set.remove(seed);
            seed += delta;
        }
        return count;
    }

    private int countDupes(int nums[]) {
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > 1) {
                count++;
            }
        }
        return count;
    }
}
