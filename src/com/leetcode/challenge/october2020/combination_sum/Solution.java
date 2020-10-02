package com.leetcode.challenge.october2020.combination_sum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3481/
class Solution {
    // can i get 0 as one of the candidates?
    // sorted?
    // what about target range and candidates range? nullness? positive or negatives only?
    // intuitively, take target, subtract by a candidate, then see if result can be formed by same set
    // maybe later cache optimize certain numbers that can't be formed. so dont waste time

    // O(n^m) time, where N is number of candidates. m is how deep the tree goes
    // O(n*m) space, where N is num of candidates, m is the depth of the branchings of the recursion stack.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new LinkedList<>();
        combine(0, new ArrayList(), 0, candidates, target, lists);
        return lists;
    }

    private void combine(int current, List<Integer> currentList, int currentIndex, int[] candidates, int target, List<List<Integer>> result) {
        if (current > target) {
            return;
        } else if (current == target) {
            result.add(currentList);
        } else {
            for (int i=currentIndex; i<candidates.length; i++) { // n, n-1, n-2, ...
                int c = candidates[i];
                List<Integer> copy = new ArrayList<>(currentList);
                copy.add(c);
                combine(current + c, copy, i, candidates, target, result); // m.
            }
        }
    }
}
