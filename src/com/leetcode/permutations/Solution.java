package com.leetcode.permutations;

import java.util.*;

class Solution {
    // perm = {a} + perm{rest}
    List<List<Integer>> wholeSet = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            set.add(nums[i]);
        }

        step(set, new LinkedList<>());

        return wholeSet;
    }

    public void step(Set<Integer> set, List<Integer> temp) {
        if (set.isEmpty()) {
            wholeSet.add(temp);
            return;
        }

        Set<Integer> copy;
        for (Integer s : set) {
            copy = new HashSet<>(set);
            copy.remove(s);
            LinkedList list = new LinkedList<>(temp);
            list.add(s);
            step(copy, list);
        }
    }
}