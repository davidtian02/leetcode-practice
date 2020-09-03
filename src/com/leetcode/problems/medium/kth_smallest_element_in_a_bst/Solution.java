package com.leetcode.problems.medium.kth_smallest_element_in_a_bst;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
class Solution {
    Integer result = null;
    int index = 1;
    public int kthSmallest(TreeNode root, int k) {
        result = null;
        index = 1;
        searchTree(root, k);
        return result;
    }

    private void searchTree(TreeNode root, int k) {
        if (root == null || result != null) {
            return;
        }

        searchTree(root.left, k);
        if (k == index && result == null) {
            result = root.val;
            return;
        } else {
            index++;
        }
        searchTree(root.right, k);
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
        Solution runner = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);
        System.out.println(runner.kthSmallest(root, 1));
    }
}
