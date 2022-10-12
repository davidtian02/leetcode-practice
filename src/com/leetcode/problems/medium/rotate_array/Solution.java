package com.leetcode.problems.medium.rotate_array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int[] nums2x = Arrays.copyOf(nums, nums.length << 1);
        System.arraycopy(nums, 0, nums2x, nums.length, nums.length);
        System.arraycopy(nums2x, 0, nums, k, nums.length - k);
        System.arraycopy(nums2x, nums.length-k, nums, 0, k);
    }

    public void rotate2(int[] nums, int k) {
        k %= nums.length;

        Queue<Integer> queue = new LinkedList<>();
        for (int i=nums.length-1; i>=0; i--) {
            queue.add(nums[i]);
        }

        int index=0;
        while (index<k) {
            queue.add(queue.remove());
            index++;
        }

        int[] temp = queue.stream().mapToInt(a -> a).toArray();
        for (int i=0; i<temp.length>>1; i++) {
            int t = temp[i];
            temp[i] = temp[temp.length-i-1];
            temp[temp.length-i-1] = t;
        }
        System.arraycopy(temp, 0, nums, 0, temp.length);
    }

    public void rotate3(int[] nums, int k) {
        // in place rotation: maybe swap by k amounts each time, from the end of array
    }
}