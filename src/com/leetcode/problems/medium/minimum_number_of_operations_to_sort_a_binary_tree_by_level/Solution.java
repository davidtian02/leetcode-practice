package com.leetcode.problems.medium.minimum_number_of_operations_to_sort_a_binary_tree_by_level;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minimumOperations(TreeNode root) {
        int count = 0;
        Queue<TreeNode> previous = new LinkedList<>();
        previous.add(root);
        while (!previous.isEmpty()) {
            Queue<TreeNode> next = new LinkedList<>();
            List<Integer> nums = new ArrayList<>();
            while (!previous.isEmpty()) {
                TreeNode temp = previous.remove();
                nums.add(temp.val);
                if (temp.left != null) {
                    next.add(temp.left);
                }
                if (temp.right != null) {
                    next.add(temp.right);
                }
            }
            List<Integer> sorted = new ArrayList<>(nums);
            Collections.sort(sorted);
            count += countSwaps(nums, sorted);

            previous = next;
        }

        return count;
    }

    private int countSwaps(List<Integer> original, List<Integer> sorted) {
        Map<Integer, Integer> mapping = new HashMap<>();
        int count=0;
        for (int i=0; i<sorted.size(); i++) {
            mapping.put(sorted.get(i), i);
        }

        for (int i=0; i<original.size(); i++) {
            int n = original.get(i);
            while (mapping.get(n) != i) {
                // swap
                int a = mapping.get(n); // 2, the place to swap to
                int temp = n; // 7
                original.set(i, original.get(a));
                original.set(a, temp);

                count++;

                n = original.get(i);
            }
        }

        return count;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}