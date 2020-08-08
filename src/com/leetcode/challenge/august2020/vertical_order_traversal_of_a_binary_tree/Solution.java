package com.leetcode.challenge.august2020.vertical_order_traversal_of_a_binary_tree;

import java.util.*;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/549/week-1-august-1st-august-7th/3415/
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<NodeWithMeta> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        PriorityQueue<NodeWithMeta> nodes = new PriorityQueue<>((me, other) -> {
            if (me.x < other.x) {
                return -1;
            } else if (me.x > other.x) {
                return 1;
            } else {
                if (me.y > other.y) {
                    return -1;
                } else if (me.y < other.y) {
                    return 1;
                } else {
                    return Integer.compare(me.node.val, other.node.val);
                }
            }
        });
        NodeWithMeta start = new NodeWithMeta(root, 0, 0);
        queue.add(start);
        nodes.add(start);
        while (!queue.isEmpty()) {
            NodeWithMeta temp = queue.remove();
            if (temp.node.left != null) {
                NodeWithMeta l = new NodeWithMeta(temp.node.left, temp.x - 1, temp.y - 1);
                queue.add(l);
                nodes.add(l);
            }
            if (temp.node.right != null) {
                NodeWithMeta r = new NodeWithMeta(temp.node.right, temp.x + 1, temp.y - 1);
                queue.add(r);
                nodes.add(r);
            }
        }

        Integer lastX = null;
        List<Integer> list = new LinkedList<>();
        result.add(list);
        while (!nodes.isEmpty()) {
            if (lastX == null) {
                lastX = nodes.peek().x;
            }
            NodeWithMeta head = nodes.remove();
            if (lastX != head.x) {
                list = new LinkedList<>();
                result.add(list);
            }
            list.add(head.node.val);
            lastX = head.x;
        }

        return result;
    }

    static class NodeWithMeta {
        TreeNode node;
        int x, y;
        NodeWithMeta(TreeNode n, int x, int y) {
            this.node = n;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.verticalTraversal(testCase1()));
//        System.out.println(runner.verticalTraversal(testCase2()));
    }

    private static TreeNode testCase1() {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        TreeNode rightleft = new TreeNode(15);
        TreeNode rightright = new TreeNode(7);
        root.left = left;
        root.right = right;
        right.left = rightleft;
        right.right = rightright;
        return root;
    }
    private static TreeNode testCase2() {
        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(1);
        return root;
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