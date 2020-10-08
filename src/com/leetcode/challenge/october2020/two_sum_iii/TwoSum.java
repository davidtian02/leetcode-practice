package com.leetcode.challenge.october2020.two_sum_iii;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/560/week-2-october-8th-october-14th/3487/
class TwoSum {
    Map<Integer, Integer> nums;
    /** Initialize your data structure here. */
    public TwoSum() {
        nums = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        nums.put(number, nums.getOrDefault(number, 0)+1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            int start = entry.getKey();
            int diff = value - start;
            if (diff == start) {
                if (entry.getValue() > 1) {
                    return true;
                } else {
                    continue;
                }
            }
            if (nums.containsKey(diff)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
