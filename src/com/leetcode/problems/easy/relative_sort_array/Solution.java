package com.leetcode.problems.easy.relative_sort_array;

// https://leetcode.com/problems/relative-sort-array/
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];

        int[] frequency = new int[1001]; // most it'll get
        for (int a : arr1) {
            frequency[a]++;
        }

        int rIndex = 0;
        for (int a : arr2) {
            while (frequency[a] > 0) {
                result[rIndex] = a;
                frequency[a]--;
                rIndex++;
            }
        }

        for (int i=0; i<frequency.length; i++) {
            while (frequency[i] > 0) {
                result[rIndex] = i;
                frequency[i]--;
                rIndex++;
            }
        }

        return result;
    }
}