package com.leetcode.challenge.september2020.combination_sum_iii;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/555/week-2-september-8th-september-14th/3457/
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new LinkedList<>();
        if (k>=n) {
            return result;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        generateList(result, temp, 0, k, n);
        return result;
    }

    private void generateList(List<List<Integer>> result, List<Integer> current, int index, int count, int target) {
        if (target == 0 && count == 0) {
            result.add(current);
        } else if (target < 0 || count <= 0) {
            // too much
            return;
        }

        for (int i=index+1; i<10; i++) {
            ArrayList<Integer> copy = new ArrayList<>(current);
            copy.add(i);
            generateList(result, copy, i, count-1, target-i);
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.combinationSum3(3, 7));
        System.out.println(runner.combinationSum3(3, 9));
        System.out.println(runner.combinationSum3(3, 0));
        System.out.println(runner.combinationSum3(10, 10));
        System.out.println(runner.combinationSum3(1, 5));
    }

}

