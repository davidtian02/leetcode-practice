package com.leetcode.problems.easy.trim_a_binary_search_tree;

// https://leetcode.com/problems/trim-a-binary-search-tree/submissions/
public class Solution {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }

        if (root.val >= L && root.val <= R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        } else {
            TreeNode leftSubRoot = trimBST(root.left, L, R);
            TreeNode rightSubRoot = trimBST(root.right, L, R);

            if (leftSubRoot == null && rightSubRoot == null) {
                return null;
            } else if (leftSubRoot == null) {
                return rightSubRoot;
            } else if (rightSubRoot == null) {
                return leftSubRoot;
            } else {
                return root.val < L ? rightSubRoot : leftSubRoot;
            }
        }
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
