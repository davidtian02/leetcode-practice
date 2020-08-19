package com.leetcode.problems.easy.robot_return_to_origin;

// https://leetcode.com/problems/robot-return-to-origin/
class Solution {
    public boolean judgeCircle(String moves) {
        long horizontal = 0;
        long vertical = 0;
        int len = moves.length();
        for (int i=0; i<len; i++) {
            char c = moves.charAt(i);
            switch (c) {
                case 'L': horizontal--; break;
                case 'R': horizontal++; break;
                case 'U': vertical++; break;
                case 'D': vertical--; break;
                default: throw new RuntimeException("invalid string moves");
            }
        }
        return horizontal == 0 && vertical == 0;
    }
}