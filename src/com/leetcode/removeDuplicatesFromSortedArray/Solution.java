package com.leetcode.removeDuplicatesFromSortedArray;

class Solution {
    public static void main(String... args) {
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

    public static int removeDuplicates(int[] nums) {
        // edge case 1 element
        if (nums.length < 2) {
            return 0;
        }

        int i=1, j=0;
        while(i<nums.length) {
            if (nums[i] <= nums[i-1]) {
                while(j < nums.length && nums[j] <= nums[i]) {
                    j++;
                }
                if (j == nums.length) {
                    return i-1;
                }
                nums[i] = nums[j];
                i--;
            }

            i++;
        }

        return i;
    }
}