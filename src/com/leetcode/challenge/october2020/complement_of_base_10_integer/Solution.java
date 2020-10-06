package com.leetcode.challenge.october2020.complement_of_base_10_integer;

import java.util.Stack;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3484/
class Solution {
    public int bitwiseComplement(int N) {
        if (N==0) return 1;
        Stack<Integer> stack = new Stack<>();
        while (N != 0) {
            stack.push(N&0x01);
            // System.out.println(stack.peek());
            N >>>=1;
        }
        // System.out.println("size is: " +stack.size());

        int result = 0;
        while (!stack.isEmpty()) {
            int t = stack.pop();
            result <<= 1;
            result += (t == 0) ? 1 : 0;
            // System.out.println("result is :: " + result);
        }

        return result;
    }
}
