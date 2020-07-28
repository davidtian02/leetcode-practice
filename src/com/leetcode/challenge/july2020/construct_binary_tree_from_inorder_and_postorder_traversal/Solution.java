package com.leetcode.challenge.july2020.construct_binary_tree_from_inorder_and_postorder_traversal;

import java.util.*;

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
// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/547/week-4-july-22nd-july-28th/3403/
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length || inorder.length == 0) { // or if elements don't match
            return null;
        }

        return buildTreeHelper(inorder, postorder);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder) {
        Map<Integer, TreeNode> seen = new HashMap<>();
        TreeNode node = null;
        for (int i=0; i<inorder.length; i++) {
            node = new TreeNode(inorder[i]);
            int indexInPostorder = findIndexInPostOrder(inorder[i], postorder);
            TreeNode parent = findKnownParent(indexInPostorder, postorder, seen);
            if (parent == null) {
                // special case where it's root, potentially. or first element
                node.left = findLeftChild(indexInPostorder, postorder, seen);
            } else {
                if (parent.right != null) {
                    node.left = parent.right;
                }
                parent.right = node;
            }
            seen.put(inorder[i], node);
        }

        return seen.get(postorder[postorder.length-1]);
    }

    private TreeNode findLeftChild(int indexInPostorder, int[] postorder, Map<Integer, TreeNode> seen) {
        for (int i=indexInPostorder; i>=0; i--) {
            if (seen.containsKey(postorder[i])) {
                return seen.get(postorder[i]);
            }
        }
        return null;
    }

    private TreeNode findKnownParent(int indexInPostorder, int[] postorder, Map<Integer, TreeNode> seen) {
        for (int i=indexInPostorder; i<postorder.length; i++) {
            if (seen.containsKey(postorder[i])) {
                return seen.get(postorder[i]);
            }
        }
        return null;
    }

    private int findIndexInPostOrder(int target, int[] postorder) {
        for (int i=0; i<postorder.length; i++) {
            if (target == postorder[i]) {
                return i;
            }
        }

        throw new RuntimeException("Can't find target in postorder: " + target);
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        TreeNode root = runner.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
//        TreeNode root = runner.buildTree(new int[]{2,1,3}, new int[]{2,3,1});
//        TreeNode root = runner.buildTree(new int[]{2,3,1}, new int[]{3,2,1});
//        TreeNode root = runner.buildTree(new int[]{1,2}, new int[]{2,1});
//        TreeNode root = runner.buildTree(new int[]{1,2,3,4}, new int[]{3,2,4,1});
        System.out.println(root);
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
