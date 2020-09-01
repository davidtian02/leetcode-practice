package com.leetcode.problems.easy.merge_two_binary_trees;

// https://leetcode.com/problems/merge-two-binary-trees/
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode root;
        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 == null) { // t2 is not
            // root = new TreeNode(t2.val);
            root = t2;
            root.left = mergeTrees(null, t2.left);
            root.right = mergeTrees(null, t2.right);
        } else if (t2 == null) {
            // root = new TreeNode(t1.val);
            root = t1;
            root.left = mergeTrees(t1.left, null);
            root.right = mergeTrees(t1.right, null);
        } else {
            // root = new TreeNode(t1.val + t2.val);
            root = t1;
            t1.val += t2.val;
            root.left = mergeTrees(t1.left, t2.left);
            root.right = mergeTrees(t1.right, t2.right);
        }
        return root;
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