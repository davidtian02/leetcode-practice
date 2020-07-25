package com.leetcode.challenge.july2020.binary_tree_zigzag_level_order_traversal;

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
// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/547/week-4-july-22nd-july-28th/3398/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean directionIsRight = true;
        while (!q.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i<size; i++) { // we only want to travel level by level
                TreeNode t = q.remove();
                temp.add(t.val);

                if (t.left != null) {
                    q.add(t.left);
                }
                if (t.right != null) {
                    q.add(t.right);
                }
            }

            if (!directionIsRight) {
                Collections.reverse(temp);
            }

            result.add(temp);

            directionIsRight = !directionIsRight;
        }

        return result;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        long start = System.nanoTime();
        List<List<Integer>> answer = runner.zigzagLevelOrder(root);
        long end = System.nanoTime();
        System.out.println(answer);
        System.out.println("duration: " + (end - start));
    }

    public static class TreeNode {
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