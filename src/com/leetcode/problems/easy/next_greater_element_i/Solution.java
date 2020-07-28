package com.leetcode.problems.easy.next_greater_element_i;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/next-greater-element-i/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // value to index reverse mapping
        for (int i=0; i<nums2.length; i++) {
            map.put(nums2[i], i);
        }

        int[] result = new int[nums1.length];
        for (int i=0; i<nums1.length; i++) {
            result[i] = findNextGreaterElement(nums1[i], map, nums2);
        }

        return result;
    }

    private int findNextGreaterElement(int target, Map<Integer, Integer> mapping, int[] nums2) {
        int index = mapping.get(target);
        for (int i=index; i<nums2.length; i++) {
            if (nums2[i] > target) {
                return nums2[i];
            }
        }
        return -1;
    }
}
