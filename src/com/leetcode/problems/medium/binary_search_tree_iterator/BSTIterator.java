package com.leetcode.problems.medium.binary_search_tree_iterator;

import java.util.LinkedList;
import java.util.Queue;

public class BSTIterator {
    Queue<TreeNode> queue;

    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        populateQueue(root);
    }

    public int next() {
        return queue.remove().val;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void populateQueue(TreeNode node) {
        if (node != null) {
            populateQueue(node.left);
            queue.add(node);
            populateQueue(node.right);
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
