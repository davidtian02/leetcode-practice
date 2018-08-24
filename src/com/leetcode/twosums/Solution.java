package com.leetcode.twosums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }

        Map<Integer, List<Integer>> mappedArray = constructMapOfIndices(nums);
        for (int i=0; i<nums.length; i++) {
            if (mappedArray.containsKey(target - nums[i])) {
                List<Integer> otherIndices = mappedArray.get(target - nums[i]);
                for (int j : otherIndices) {
                    if (i != j) {
                        int[] solution = new int[]{i, j};
                        if (solution[0] < solution[1]) {
                            return solution;
                        } else {
                            return new int[]{solution[1], solution[0]};
                        }
                    }
                }
            }
        }

        return null;
    }

    private Map<Integer, List<Integer>> constructMapOfIndices(int[] originalInput) {
        Map<Integer, List<Integer>> temp = new HashMap<>();
        for (int i=0; i<originalInput.length; i++) {
            List<Integer> list;
            if (temp.containsKey(originalInput[i])) {
                list = temp.get(originalInput[i]);
            } else {
                list = new LinkedList<>();
            }
            list.add(i); // since i represents original index
            temp.put(originalInput[i], list);
        }
        return temp;
    }
}