package com.leetcode.problems.medium.balance_a_binary_search_tree;

import java.util.ArrayList;

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
// https://leetcode.com/problems/balance-a-binary-search-tree/submissions/
class Solution {
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        ArrayList<Integer> arr = new ArrayList<>();
        convert(root, arr);
        return createTree(arr);
    }

    // because sorted, in-order should produce... in-order.
    private void convert(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        convert(root.left, arr);
        arr.add(root.val);
        convert(root.right, arr);
    }

    private TreeNode createTree(ArrayList<Integer> arr) {
        return rebuild(arr, 0, arr.size()-1);
    }

    // take mid, partition left and right
    private TreeNode rebuild(ArrayList<Integer> arr, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(arr.get(start));
        }

        int mid = ((end-start) >> 1) + start;
        TreeNode root = new TreeNode(arr.get(mid));
        root.left = rebuild(arr, start, mid-1);
        root.right = rebuild(arr, mid+1, end);
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
        Solution runner = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root = runner.balanceBST(root);
        System.out.println(root);
    }
}