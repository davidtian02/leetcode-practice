package com.leetcode.problems.medium.kth_smallest_element_in_a_sorted_matrix;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/submissions/
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // node: [val, row, col]

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
        );
        for (int i=0; i<matrix.length; i++) {
            pq.add(new int[]{matrix[i][0], i, 0});
        }

        int[] node = null;

        for (int i=0; i<k; i++) {
            node = pq.remove();
            int row = node[1];
            int col = node[2];
            if (col+1 < matrix.length) {
                int[] temp = new int[]{matrix[row][col+1], row, col+1};
                pq.add(temp);
            }
        }

        return node[0];
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        int[][] input = new int[][]{
                {1,5,9},
                {10,11,13},
                {12,13,15}
        };
        System.out.println(runner.kthSmallest(input, 8));
    }
}
