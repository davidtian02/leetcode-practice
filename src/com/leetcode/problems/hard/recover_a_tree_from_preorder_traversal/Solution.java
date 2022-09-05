package com.leetcode.problems.hard.recover_a_tree_from_preorder_traversal;

import java.util.LinkedList;
import java.util.Stack;

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

    public TreeNode recoverFromPreorder(String traversal) {
        LinkedList<Token> tokens = parse(traversal);
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tn = new TreeNode(tokens.removeFirst().val);
        stack.push(tn);
        int currentLevel = 0;
        TreeNode root = tn;

        for (Token t : tokens) {
            TreeNode parent = stack.peek();
            tn = new TreeNode(t.val);
            boolean hasPopped = false;

            while (t.level != currentLevel+1) {
                stack.pop();
                parent = stack.peek();
                hasPopped = true;
                currentLevel--;
            }

            if (!hasPopped) {
                parent.left = tn;
            } else {
                parent.right = tn;
            }

            stack.push(tn);
            currentLevel++;
        }

        return root;
    }

    private LinkedList<Token> parse(String input) {
        // TODO prolly use regex
        LinkedList<Token> list = new LinkedList<>();

        int i=0;
        while (i<input.length()) {
            StringBuilder sb = new StringBuilder();
            // get levels
            int level = 0;
            while (input.charAt(i) == '-') {
                level++;
                i++;
            }
            // get value
            while (i<input.length() && input.charAt(i) != '-') {
                sb.append(input.charAt(i));
                i++;
            }
            Token t = new Token(level, Integer.parseInt(sb.toString()));
            list.add(t);
        }
        return list;
    }

    private static class Token {
        int level;
        int val;
        Token(int l, int v) {
            level = l;
            val = v;
        }

        @Override
        public String toString() {
            return "(level: " + level + ", " + val + ")";
        }
    }

    public static class TreeNode {
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
