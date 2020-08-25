package com.leetcode.problems.hard.minimum_number_of_k_consecutive_bit_flips;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
class Solution {

    // the solution we use here is... imagine that when flipping bits, we are applying a huge brick across
    // the entire array from the start index of the flip to the last index of the flip.
    // then we only need to store the last element of the index to flip.
    // if we get another long brick in between, then we add it to a queue, and we use the SIZE of the queue to note
    // how many flips we are doing at the current index. and then of course, pop the "brick" once you reach the index
    // obviously add one to flip count when adding a brick
    public int minKBitFlips(int[] A, int K) {
        int flipCount = 0;
        Queue<Integer> bricks = new LinkedList<>();

        for (int i=0; i<A.length; i++) {
            if (A[i] == (bricks.size() & 0x01)) {
                // requires flip
                flipCount++;
                bricks.add(i+K-1);
            }

            if (!bricks.isEmpty() && (bricks.peek() == i)) {
                bricks.remove();
            }
        }

        return bricks.isEmpty() ? flipCount : -1;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.minKBitFlips(new int[]{0,0,0,1,0,1,1,0}, 3));
    }
}