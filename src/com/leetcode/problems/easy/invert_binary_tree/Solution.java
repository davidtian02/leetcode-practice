package com.leetcode.problems.easy.invert_binary_tree;

import java.util.Stack;

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
// https://leetcode.com/problems/invert-binary-tree/submissions/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            int size = stack.size();
            for (int i=0; i<size; i++) {
                TreeNode head = stack.pop();
                TreeNode temp = null;
                if (head.left != null) {
                    stack.push(head.left);
                    temp = head.left;
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
                head.left = head.right;
                head.right = temp;
            }
        }

        return root;
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

    public static void main(String... args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        Solution runner = new Solution();
        System.out.println(runner.invertTree(root));
    }
}