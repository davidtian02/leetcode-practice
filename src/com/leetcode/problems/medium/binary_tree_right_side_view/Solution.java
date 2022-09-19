package com.leetcode.problems.medium.binary_tree_right_side_view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightViewNodes = new LinkedList<>();
        Queue<TreeNode> levels = new LinkedList<>();
        if (root != null) {
            levels.add(root);
            rightViewNodes.add(root.val);
            while (!levels.isEmpty()) {
                LinkedList<TreeNode> temp = new LinkedList<>();
                while(!levels.isEmpty()) {
                    TreeNode node = levels.remove();
                    if (node.left != null) {
                        temp.add(node.left);
                    }
                    if (node.right != null) {
                        temp.add(node.right);
                    }
                }
                levels = temp;
                if (!temp.isEmpty()) {
                    rightViewNodes.add(temp.getLast().val);
                }
            }
        }

        return rightViewNodes;
    }

    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> rightViewNodes = new ArrayList<>();
        traverse(rightViewNodes, root, 0);

        return rightViewNodes;
    }

    // post order traversal
    private void traverse(List<Integer> rightViewNodes, TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level > rightViewNodes.size()-1) {
            rightViewNodes.add(root.val);
        }

        traverse(rightViewNodes, root.left, level+1);
        traverse(rightViewNodes, root.right, level+1);

        rightViewNodes.set(level, root.val);
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        System.out.println(runner.rightSideView(root));
    }

    private static void test2() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(15);
        System.out.println(runner.rightSideView(root));
    }

    private static void test3() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(1);
        System.out.println(runner.rightSideView(root));
    }

    private static void test4() {
        Solution runner = new Solution();
        System.out.println(runner.rightSideView(null));
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
