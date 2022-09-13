package com.leetcode.problems.easy.convert_sorted_array_to_binary_search_tree;

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return partition(nums, 0, nums.length-1);
    }

    private TreeNode partition(int[] nums, int left, int right) {
        if (left == right) { // more edge cases here
            return new TreeNode(nums[left]);
        } else if (left+1 == right) {
            TreeNode root = new TreeNode(nums[right]);
            root.left = new TreeNode(nums[left]);
            return root;
        } else {
            int mid = ((right - left) >> 1) + left;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = partition(nums, left, mid-1);
            root.right = partition(nums, mid+1, right);
            return root;
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