package com.leetcode.problems.medium.jump_game;

// https://leetcode.com/problems/jump-game/
class Solution {
    public boolean canJump(int[] nums) {
        Boolean[] cache = new Boolean[nums.length];
        cache[cache.length-1] = true;
        return canJump(nums, 0, cache);
    }

    private boolean canJump(int[] nums, int start, Boolean[] cache) {
        if (start >= nums.length-1) {
            return true;
        } else if (nums[start] == 0) {
            return false;
        }
        if (cache[start] != null) {
            return cache[start];
        }

        boolean found = false;
        for (int i=nums[start]; i>0; i--) { // jump as far as u can to start with
            found = found || canJump(nums, start+i, cache);
        }
        cache[start] = found;
        return found;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.canJump(new int[]{1}));
        System.out.println(runner.canJump(new int[]{0}));
        System.out.println(runner.canJump(new int[]{0,0,0}));
        System.out.println(runner.canJump(new int[]{1,0}));
        System.out.println(runner.canJump(new int[]{2,0}));
        System.out.println(runner.canJump(new int[]{1,0,4}));
        System.out.println(runner.canJump(new int[]{2,1,0,5}));
        System.out.println(runner.canJump(new int[]{3,4,0,1,2,5}));
    }
}
