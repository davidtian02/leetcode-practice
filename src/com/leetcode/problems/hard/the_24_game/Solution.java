package com.leetcode.problems.hard.the_24_game;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/24-game/
class Solution {
    public boolean judgePoint24(int[] nums) {
        // you can combine a,b together via 6 combinations, and then roll c into the combo, and then roll d into combo, to see if contains 24
        // do this again but for a,b and c,d together.
        int[][] abCombo = new int[][]{
                {0,1}, {0,2}, {0,3},
                {1,2}, {1,3},
                {2,3},
        };
        int[][] cdCombo = new int[][]{
                {2,3}, {1,3}, {1,2},
                {0,3}, {0,2},
                {0,1},
        };

        return determine24FromTwoAndTwo(24, nums, abCombo, cdCombo) || determine24FromThreeAndOne(24, nums, abCombo, cdCombo);
    }

    private boolean determine24FromThreeAndOne(int target, int[] nums, int[][] abCombo, int[][] cdCombo) {
        for (int i=0; i<abCombo.length; i++) { // oops shoulda combined ab and cd combos into one matrix
            Set<Float> abBag = makeCombination(nums[abCombo[i][0]], nums[abCombo[i][1]]); // uses like {0,1}
            for (Float ab : abBag) {
                Set<Float> abcBag = makeCombination(ab, nums[cdCombo[i][0]]); // uses like {0,1} and 2
                for (Float abc : abcBag) {
                    Set<Float> abcdBag = makeCombination(abc, nums[cdCombo[i][1]]); // uses like {{0,1}, 2} and 3
                    if (contains(abcdBag, target)) {
                        return true;
                    }
                }
            }
            for (Float ab : abBag) {
                Set<Float> abdBag = makeCombination(ab, nums[cdCombo[i][1]]); // uses like {0,1} and 2
                for (Float abd : abdBag) {
                    Set<Float> abcdBag = makeCombination(abd, nums[cdCombo[i][0]]); // uses like {{0,1}, 3} and 2
                    if (contains(abcdBag, target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean determine24FromTwoAndTwo(int target, int[] nums, int[][] abCombo, int[][] cdCombo) {
        for (int i=0; i<abCombo.length; i++) { // oops shoulda combined ab and cd combos into one matrix
            Set<Float> abBag = makeCombination(nums[abCombo[i][0]], nums[abCombo[i][1]]); // uses like {0,1}
            Set<Float> cdBag = makeCombination(nums[cdCombo[i][0]], nums[cdCombo[i][1]]); // uses like {0,1}
            Set<Float> flatResultBag = new HashSet<>();
            for (Float ab : abBag) {
                for (Float cd : cdBag) {
                    flatResultBag.addAll(makeCombination(ab, cd));
                }
            }
            if (contains(flatResultBag, target)) {
                return true;
            }
        }
        return false;
    }

    private Set<Float> makeCombination(float a, float b) {
        Set<Float> result = new HashSet<>(); // TODO dont use floats
        result.add(a+b);
        result.add(a-b);
        result.add(a*b);
        if (!equals(b, 0f)) {
            result.add(a/b);
        }
        // b+a == a+b, so no need
        result.add(b-a);
        // b*a == a*b, so no need
        if (!equals(a, 0f)) {
            result.add(b/a);
        }
        return result;
    }

    // FIXME this is pretty hacky
    private boolean contains(Set<Float> set, float n) {
        if (set.contains(n)) {
            return true;
        }

        for (Float s : set) {
            if (equals(s, n)) {
                return true;
            }
        }

        return false;
    }

    // FIXME is there really not a built in way to do float equals? wtf?
    private boolean equals(float a, float b) {
        return Math.abs(a-b) < 0.0001f;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println(runner.judgePoint24(new int[]{1, 2, 1, 2}));
        System.out.println(runner.judgePoint24(new int[]{3, 3, 8, 8})); // 8/(3 - 8/3)
        Set<Float> floats = new HashSet<>();
        floats.add((float) 24);
        float val = 8.0f / (3.0f - 8.0f / 3.0f);
        System.out.println(val);
        System.out.println(floats.contains(val));
        System.out.println(runner.contains(floats, val));
    }
}