package com.leetcode.problems.medium.wiggle_subsequence;

class Solution {
    public int wiggleMaxLength(int[] nums) {
        // you're finding peaks and troughs, more specifically the number of
        int turns = 1;
        Boolean isUp = null;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] > nums[i-1]) { // going up
                    if (isUp == null || !isUp) {
                        // if was going down then suddenly up, then it's a turn in directions
                        turns++;
                        isUp = true;
                    }
                } else { // going down
                    if (isUp == null || isUp) {
                        turns++;
                        isUp = false;
                    }
                }
            }
        }

        return turns;
    }
}
