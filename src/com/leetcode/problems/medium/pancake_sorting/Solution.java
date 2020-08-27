package com.leetcode.problems.medium.pancake_sorting;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/pancake-sorting/
class Solution {
    public List<Integer> pancakeSort(int[] A) {
        // find largest and then flip twice?
        int max = A.length;
        List<Integer> result = new LinkedList<>();

        for (int i=max; i>=0; i--) {
            findAndDoubleFlip(A, i, i-1, result); // find 4 and put it in index 3
        }

        return result;
    }

    private void findAndDoubleFlip(int[] A, int target, int targetIndex, List<Integer> result) {
        int indexToFlip = -1;
        for (int i=0; i<=targetIndex; i++) {
            if (A[i] == target) {
                indexToFlip = i;
                break;
            }
        }

        if (indexToFlip == targetIndex) {
            return;
        }

        if (indexToFlip != 0) {
            result.add(indexToFlip+1);
        }

        // flip ... TODO maybe can optimize double flipping?
        flipPancake(A, indexToFlip);

        result.add(targetIndex+1);
        flipPancake(A, targetIndex);
    }

    private void flipPancake(int[] A, int index) {
        int mid = index>>1;
        for (int i=0; i<=mid; i++) {
            int temp = A[i];
            A[i] = A[index-i];
            A[index-i] = temp;
        }
    }
}