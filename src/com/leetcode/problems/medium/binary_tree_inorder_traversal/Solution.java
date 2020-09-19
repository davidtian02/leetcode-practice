package com.leetcode.problems.medium.binary_tree_inorder_traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// https://leetcode.com/problems/binary-tree-inorder-traversal/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        traverseHelper2(root, result);
        return result;
    }

    // https://leetcode.com/submissions/detail/175895013/
    private void traverseHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        traverseHelper(root.left, list);
        list.add(root.val);
        traverseHelper(root.right, list);
    }

    // https://leetcode.com/submissions/detail/176271226/
    private void traverseHelper2(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        TreeNode current = root;
        boolean isSameCallStack = false;
        while (!stack.isEmpty()) {
            if (!isSameCallStack) { //  isSameCallStack tries to emulate the function stack from recursion
                while (current.left != null) {
                    stack.push(current.left);
                    current = current.left;
                    System.out.println("X " + current.val);
                }
            }

            current = stack.pop();
            list.add(current.val);

            if (current.right != null) {
                stack.push(current.right);
                current = current.right;
                isSameCallStack = false;
            } else {
                isSameCallStack = true;
            }
        }
    }
}