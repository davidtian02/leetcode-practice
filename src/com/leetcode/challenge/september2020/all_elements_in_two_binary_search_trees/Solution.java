package com.leetcode.challenge.september2020.all_elements_in_two_binary_search_trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3449/
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // create 2 queues needed for storing the order.
        Queue<Integer> l1 = convertTreeToQueue(root1, new LinkedList<>());
        Queue<Integer> l2 = convertTreeToQueue(root2, new LinkedList<>());

        // then merge the 2 queues together
        return mergeLists(l1, l2);
    }

    private Queue<Integer> convertTreeToQueue(TreeNode root, LinkedList<Integer> nums) {
        if (root == null) {
            return nums;
        }

        convertTreeToQueue(root.left, nums);
        nums.add(root.val);
        convertTreeToQueue(root.right, nums);
        return nums;
    }

    private List<Integer> mergeLists(Queue<Integer> l1, Queue<Integer> l2) {
        List<Integer> result = new LinkedList<>();
        while (!l1.isEmpty() || !l2.isEmpty()) { // while still have elemnts
            if (l1.isEmpty()) {
                result.add(l2.remove());
            } else if (l2.isEmpty()) {
                result.add(l1.remove());
            } else {
                result.add(l1.peek() < l2.peek() ? l1.remove() : l2.remove());
            }
        }

        return result;
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
