package com.leetcode.problems.medium.subsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    // here https://leetcode.com/submissions/detail/171373433/
    Set<List<Integer>> mMasterSet = new HashSet<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> copy = new ArrayList<>();
        for (int n : nums) {
            copy.add(n);
        }

        subsetHelper(copy);

        List<List<Integer>> result = new ArrayList<>();
        result.add(copy);
        for (List<Integer> e : mMasterSet) {
            result.add(e);
        }

        return result;
    }

    private void subsetHelper(List<Integer> nums) {
        if (nums.size() < 1) {
            return;
        }

        for (int i=0; i<nums.size(); i++) {
            List<Integer> copy = new ArrayList<>(nums);
            copy.remove(i);
            mMasterSet.add(copy);
            subsetHelper(copy);
        }
    }
}