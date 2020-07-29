package com.leetcode.problems.medium.top_k_frequent_elements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/top-k-frequent-elements/
public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (frequency.containsKey(nums[i])) {
                frequency.put(nums[i], frequency.get(nums[i]) + 1);
            } else {
                frequency.put(nums[i], 1);
            }
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((me, other) -> {
            if (me.getValue() > other.getValue()) {
                return -1;
            } else if (me.getValue() < other.getValue()) {
                return 1;
            } else {
                return 0;
            }
        });
        pq.addAll(frequency.entrySet());

        int[] result = new int[k];
        for (int i=0; i<k; i++) {
            result[i] = pq.poll().getKey();
        }
        return result;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(Arrays.toString(runner.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
    }
}
