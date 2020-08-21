package com.leetcode.challenge.august2020.path_sum_iii;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/550/week-2-august-8th-august-14th/3417/
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, new ArrayList<>(), sum);
    }

    private int dfs(TreeNode root, List<Integer> parentSums, int target) {
        int count = 0;

        if (root.val == target) {
            count++;
        }
        for (int i=0; i<parentSums.size(); i++) {
            int prev = parentSums.get(i);
            if (root.val + prev == target) {
                count++;
            }
            parentSums.set(i, root.val+prev);
        }

        if (root.left != null) {
            List<Integer> left = new ArrayList<>(parentSums);
            left.add(root.val);
            count += dfs(root.left, left, target);
        }

        if (root.right != null) {
            List<Integer> right = new ArrayList<>(parentSums);
            right.add(root.val);
            count += dfs(root.right, right, target);
        }

        return count;
    }


    public int pathSum2(TreeNode root, int sum) {
        // since only 1000, prolly BFS is okay i guess?
        int count = 0;
        if (root == null) {
            return count;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.remove();
            if (temp.val == sum) {
                count++;
            }

            if (temp.left != null) {
                count += exploreSum(temp.left, temp.val, sum);
                q.add(temp.left);
            }
            if (temp.right != null) {
                count += exploreSum(temp.right, temp.val, sum);
                q.add(temp.right);
            }
        }
        return count;
    }

    private int exploreSum(TreeNode root, int parentSum, int targetSum) {
        int count = 0;

        if (root.val + parentSum == targetSum) {
            count++;
        }

        if (root.left != null) {
            count += exploreSum(root.left, root.val + parentSum, targetSum);
        }
        if (root.right != null) {
            count += exploreSum(root.right, root.val + parentSum, targetSum);
        }

        return count;
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

        TreeNode root = testCase1();
        System.out.println(runner.pathSum(root, 8));

//        root = testCase2();
//        System.out.println(runner.pathSum(root, 1));
//
//        root = testCase3();
//        System.out.println(runner.pathSum(root, 22));
//
//        root = testCase4();
//        System.out.println(runner.pathSum(root, 2));
//
//        root = testCase5();
//        System.out.println(runner.pathSum(root, 3));
    }

    private static TreeNode testCase5() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        return root;
    }

    private static TreeNode testCase4() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        return root;
    }

    private static TreeNode testCase3() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        return root;
    }

    private static TreeNode testCase2() {
        return new TreeNode(1);
    }

    private static TreeNode testCase1() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);
        return root;
    }
}