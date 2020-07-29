package com.leetcode.problems.medium.find_bottom_left_tree_value;

// https://leetcode.com/problems/find-bottom-left-tree-value/
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        helper(root, 1);
        return targetVal;
    }

    private int depth = 0;
    private int targetVal = -1;
    private void helper(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level > depth) {
            targetVal = root.val;
            depth = level;
        }

        if (root.left != null) {
            helper(root.left, level+1);
        }
        if (root.right != null) {
            helper(root.right, level+1);
        }
    }

    public class TreeNode {
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
