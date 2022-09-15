package com.leetcode.problems.easy.balanced_binary_tree;

class Solution {
    public boolean isBalanced(TreeNode root) {
        return traverse(root, 0) >= 0;
    }

    private int traverse(TreeNode root, int depth) {
        if (root == null || depth < 0) {
            return depth;
        }

        int left = traverse(root.left, depth+1);
        int right = traverse(root.right, depth+1);
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Integer.max(left, right);
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
