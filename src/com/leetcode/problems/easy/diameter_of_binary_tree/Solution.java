package com.leetcode.problems.easy.diameter_of_binary_tree;

class Solution {
    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        traverse(root);
        return max;
    }

    private int traverse(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int left = 1 + traverse(root.left);
        int right = 1 + traverse(root.right);

        int lenLonger = Integer.max(left, right) - 1;

        if (lenLonger > max) {
            max = lenLonger;
        }

        if (left+right > max) {
            max = left+right;
        }

        return lenLonger + 1;
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
