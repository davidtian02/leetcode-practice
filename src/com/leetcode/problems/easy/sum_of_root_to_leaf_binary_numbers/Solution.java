package com.leetcode.problems.easy.sum_of_root_to_leaf_binary_numbers;

// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/submissions/
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return sumHelper(root, 0);
    }

    private int sumHelper(TreeNode root, int current) {
        if (root == null) {
            return 0;
        }

        current = (current<<1) + root.val;
        if (root.left == null && root.right == null) {
            return current;
        }

        return sumHelper(root.left, current) + sumHelper(root.right, current);
    }


    int sum = 0;
    public int sumRootToLeaf2(TreeNode root) {
        sumPaths(root, 0);
        return sum;
    }

    private void sumPaths(TreeNode root, int parent) {
        if (root == null) {
            return;
        }

        int path = (parent<<1) + root.val;
        if (root.left == null && root.right == null) {
            sum += path;
            return;
        }

        sumPaths(root.left, path);
        sumPaths(root.right, path);
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