package com.leetcode.problems.medium.find_bottom_left_tree_value;

// https://leetcode.com/problems/find-bottom-left-tree-value/
class Solution {
    private int depth = 0;
    private int leftmostValue = -1;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return leftmostValue;
    }

    private void dfs(TreeNode root, int level) {
        if (level > depth) { // if we have a higher depth, we store the value because in DFS, we hit the leftmost value FIRST.
            leftmostValue = root.val;
            depth = level;
        }

        // children are at one level beyond the parent's level, so the children's depth is just the parent/current level + 1
        if (root.left != null) {
            dfs(root.left, level + 1);
        }
        if (root.right != null) {
            dfs(root.right, level + 1);
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

    public static void main(String... args) {

    }
}
