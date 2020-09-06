package com.leetcode.problems.medium.combination_sum;

import java.util.*;

// https://leetcode.com/problems/combination-sum/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        findCombos(candidates, 0, target, result, new ArrayList<>());
        return result;
    }

    private void findCombos(int[] candidates, int startingIndex, int target, List<List<Integer>> result, List<Integer> current) {
        if (target == 0) {
            // found answer
            result.add(current);
            return;
        } else if (target < 0) {
            return;
        }

        for (int i=startingIndex; i<candidates.length; i++) {
            List<Integer> copy = new ArrayList<>(current);
            copy.add(candidates[i]);
            findCombos(candidates, i, target-candidates[i], result, copy);
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.combinationSum(new int[]{7,3,2,6}, 8));
        System.out.println(runner.combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println(runner.combinationSum(new int[]{1}, 1));
        System.out.println(runner.combinationSum(new int[]{100}, 2));
    }

}