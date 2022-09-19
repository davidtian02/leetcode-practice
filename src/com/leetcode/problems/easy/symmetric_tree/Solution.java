package com.leetcode.problems.easy.symmetric_tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public boolean isSymmetric(TreeNode root) {
        return checkSymIterative(root);
//        return checkSymRecursive(root.left, root.right);
    }

    private boolean checkSymRecursive(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }

        boolean leftMirror = checkSymRecursive(root1.left, root2.right);
        if (root1.val != root2.val) {
            return false;
        }
        boolean rightMirror = checkSymRecursive(root1.right, root2.left);

        return leftMirror && rightMirror;
    }

    private boolean checkSymIterative(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<TreeNode> temp = new LinkedList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.removeFirst();
                if (node != null) {
                    temp.add(node.left);
                    temp.add(node.right);
                }
            }

            if (!listIsMirror(new LinkedList<>(temp))) {
                return false;
            }
            queue = temp;
        }

        return true;
    }

    // note: destroys queue for faster check
    private boolean listIsMirror(LinkedList<TreeNode> queue) {
        int mid = queue.size() >> 1;
        for (int i=0; i<mid; i++) {
            TreeNode first = queue.removeFirst();
            TreeNode last = queue.removeLast();
            if (first == null || last == null) {
                if (first != last) {
                    return false;
                } else {
                    continue;
                }
            }

            if (first.val != last.val) {
                return false;
            }

        }
        return true;
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
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(runner.isSymmetric(root));
    }

    private static void test2() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        System.out.println(runner.isSymmetric(root));
    }

    private static void test3() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(3);
        System.out.println(runner.isSymmetric(root));
    }

    private static void test4() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(1);
        System.out.println(runner.isSymmetric(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
