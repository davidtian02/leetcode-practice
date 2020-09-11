package com.leetcode.problems.medium.house_robber_iii;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/house-robber-iii/submissions/
class Solution {
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> cache = new HashMap<>();
        return robin(root, cache);
    }

    private int robin(TreeNode root, Map<TreeNode, Integer> cache) {
        if (root == null || root.val < 0) {
            return 0;
        }

        if (cache.containsKey(root)) {
            return cache.get(root);
        }

        final int max;

        // either pick root, no children
        int rootWithGrandchrildren = root.val;
        int a=0,b=0,c=0,d=0;
        if (root.left != null) {
            a = robin(root.left.left, cache);
            b = robin(root.left.right, cache);
            rootWithGrandchrildren += a + b;
        }
        if (root.right != null) {
            c = robin(root.right.left, cache);
            d = robin(root.right.right, cache);
            rootWithGrandchrildren += c + d;
        }

        // or pick children but not root
        int childrenOnly = robin(root.left, cache) + robin(root.right, cache);

        max = Math.max(rootWithGrandchrildren, childrenOnly);
        cache.put(root, max);
        return max;
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
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    private static void test1() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(runner.rob(root));
    }

    private static void test2() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(runner.rob(root));
    }
    private static void test3() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(999);
        System.out.println(runner.rob(root));
    }

    private static void test4() {
        Solution runner = new Solution();
        System.out.println(runner.rob(null));
    }

    private static void test5() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(-99);
        System.out.println(runner.rob(root));
    }

    private static void test6() {
        Solution runner = new Solution();
        TreeNode root = new TreeNode(400);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(400);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);
        root.right.right.right = new TreeNode(99);
        System.out.println(runner.rob(root));
    }
}

