package com.leetcode.problems.medium.deepest_leaves_sum;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> previous = new LinkedList<>();
        Queue<TreeNode> next = new LinkedList<>();
        previous.add(root);
        int sum = -1;
        while (!previous.isEmpty()) {
            sum = 0;
            while (!previous.isEmpty()) {
                TreeNode temp = previous.remove();
                if (temp.left != null) next.add(temp.left);
                if (temp.right != null) next.add(temp.right);
                sum += temp.val;
            }
            previous = next;
            next = new LinkedList<>();
        }

        return sum;
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
