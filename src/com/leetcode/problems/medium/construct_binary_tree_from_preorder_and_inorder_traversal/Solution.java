package com.leetcode.problems.medium.construct_binary_tree_from_preorder_and_inorder_traversal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
    // 11:20
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);
        Map<Integer, TreeNode> seen = new HashMap<>();
        seen.put(rootVal, root);
        int inOrderCursor = -1;
        for (int i=0; i<inorder.length; i++) {
            if (inorder[i] == rootVal) {
                inOrderCursor = i;
            }
        }

        for (int i=1; i<preorder.length; i++) {
            int target = preorder[i];
            TreeNode newNode = new TreeNode(target);

            int left = inOrderCursor - 1;
            boolean foundOnLeftSide = false;
            while (left >= 0) {
                if (inorder[left] == target) {
                    inOrderCursor = left;
                    foundOnLeftSide = true;
                    root.left = newNode;
                    break;
                }
                left--;
            }

            if (!foundOnLeftSide) {
                int right = inOrderCursor + 1;
                while (right < inorder.length) {
                    if (seen.containsKey(inorder[right])) {
                        root = seen.get(inorder[right]);
                    }
                    if (inorder[right] == target) {
                        inOrderCursor = right;
                        root.right = newNode;
                        break;
                    }
                    right++;
                }
            }

            root = newNode;
            seen.put(target, newNode);
        }

        return seen.get(rootVal);
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    private static void test1() {
        Solution runner = new Solution();
        int preorder[] = new int[]{3,9,20,15,7};
        int inorder[] = new int[]{9,3,15,20,7};
        printTree(runner.buildTree(preorder, inorder));
        System.out.println("--------------------------");
    }

    private static void test2() {
        Solution runner = new Solution();
        int preorder[] = new int[]{-1};
        int inorder[] = new int[]{-1};
        printTree(runner.buildTree(preorder, inorder));
        System.out.println("--------------------------");
    }

    private static void test3() {
        Solution runner = new Solution();
        int preorder[] = new int[]{1,2,3,4,5,6,7};
        int inorder[] = new int[]{1,3,5,7,6,4,2};
        printTree(runner.buildTree(preorder, inorder));
        System.out.println("--------------------------");
    }

    private static void test4() {
        Solution runner = new Solution();
        int preorder[] = new int[]{1,2,4,5,3,6,7};
        int inorder[] = new int[]{4,2,5,1,6,3,7};
        printTree(runner.buildTree(preorder, inorder));
        System.out.println("--------------------------");
    }

    private static void test5() {
        Solution runner = new Solution();
        int preorder[] = new int[]{3,2,1,4};
        int inorder[] = new int[]{1,2,3,4};
        printTree(runner.buildTree(preorder, inorder));
        System.out.println("--------------------------");
    }

    private static void printTree(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            LinkedList<TreeNode> line = new LinkedList<>();
            while (!q.isEmpty()) {
                TreeNode node = q.removeFirst();
                if (node != null) {
                    line.add(node.left);
                    line.add(node.right);
                    System.out.print("" + node.val + ", ");
                } else {
                    System.out.print("null, ");
                }
            }
            System.out.println();
            q = line;
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