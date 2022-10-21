package com.leetcode.problems.medium.flatten_binary_tree_to_linked_list;

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
class Solution {
    private TreeNode globalParent;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        globalParent = new TreeNode(0); // extra marker
        TreeNode globalParentCopy = globalParent;
        globalParent.left = root;
        traverseReverse(root);
        globalParent.left = null;
        globalParent.right = null;

        globalParentCopy = globalParentCopy.left;

        reverse(globalParentCopy);
    }

    // this is postOrder traversal, with operating last
    private void traverseReverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverseReverse(root.right);
        traverseReverse(root.left);

        globalParent.right = null;
        globalParent.left = root;
        globalParent = globalParent.left; // left is reverse
    }

    private void reverse(TreeNode root) {
        while (root.left != null) {
            root.left.right = root;
            root = root.left;
            root.right.left = null;
        }
    }

    // ============= O(1) space, excluding stack space, recursive flipping =============

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        globalParent = new TreeNode(0); // extra marker
        TreeNode globalParentCopy = globalParent;
        globalParent.left = root;
        traverseReverse(root);
        globalParent.left = null;
        globalParent.right = null; // needed?

        globalParentCopy = globalParentCopy.left;

        reverse2(globalParentCopy);
        flipLeftAndRight(root);
    }

    private void flipLeftAndRight(TreeNode root) {
        while (root != null) {
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
    }

    private void reverse2(TreeNode root) {
        TreeNode result = reverseHelper(root, root.left);
        result.left = null;
    }

    private TreeNode reverseHelper(TreeNode current, TreeNode next) {
        if (next == null) {
            globalParent = current;
            return current;
        }

        TreeNode parent = reverseHelper(next, next.left);
        parent.left = current;
        return current;
    }

    // ============= naive solution =============

    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        traverse(root, list);

        for (int i=0; i<list.size()-1; i++) {
            list.get(i).right = list.get(i+1);
            list.get(i).left = null;
        }
    }

    private void traverse(TreeNode current, ArrayList<TreeNode> list) {
        if (current == null) {
            return;
        }

        list.add(current);
        traverse(current.left, list);
        traverse(current.right, list);
    }

    // ============= helpers =============

    private String strListLeft(TreeNode root) {
        StringBuilder sb = new StringBuilder("[");
        while (root != null) {
            sb.append("" + root.val + ", ");
            root = root.left;
        }
        return sb.append(']').toString();
    }

    private String strListRight(TreeNode root) {
        StringBuilder sb = new StringBuilder("[");
        while (root != null) {
            sb.append("" + root.val + ", ");
            root = root.right;
        }
        return sb.append(']').toString();
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
