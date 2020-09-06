package com.leetcode.problems.medium.find_the_duplicate_number;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/find-the-duplicate-number/
class Solution {

    // there must be a cycle, because the only way to NOT have a cycle is [1,2,3,4] for index [0,1,2,3]. but they said that vals are from 1 to 3. so we must have at least 1 duplicate
    // if there is a cycle, there is no escaping that nums[0] is the head, because nothing else points to nums[0]
    public int findDuplicate(int[] nums) {
        int turtle=0, hare=0;
        do {
            turtle = nums[turtle];
            hare = nums[nums[hare]];
        } while(nums[turtle] != nums[hare]);

        int dupe;

        // after they match, put turtle to beginning, and move hare one at a time
        turtle = 0;
        while (nums[turtle] != nums[hare]) {
            turtle = nums[turtle];
            hare = nums[hare];
        }

        return nums[turtle];
    }

    public int findDuplicate2(int[] nums) {
        // take the sum...?
        // if tradeoff for time (preferred), simply make extra array and copy over, stop when duplicate found
        // if tradeoff for space, sort
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return n;
            }
            set.add(n);
        }
        return -1;
    }
}
