package com.leetcode.RecoverBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // https://leetcode.com/submissions/detail/176332809/
    public void recoverTree2(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        traverse(root, list);
        int errorIndex1 = -1;
        int errorIndex2 = -1;
        for (int i=0; i<list.size() - 1; i++) {
            if (list.get(i).val > list.get(i+1).val) {
                if (errorIndex1 == -1) {
                    errorIndex1 = i;
                } else {
                    errorIndex2 = i+1;
                }
            }
        }

        if (errorIndex2 == -1) {
            int temp = list.get(errorIndex1).val;
            list.get(errorIndex1).val = list.get(errorIndex1 + 1).val;
            list.get(errorIndex1 + 1).val = temp;
        } else {
            int temp = list.get(errorIndex1).val;
            list.get(errorIndex1).val = list.get(errorIndex2).val;
            list.get(errorIndex2).val = temp;
        }
    }

    private void traverse(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        traverse(root.left, list);
        list.add(root);
        traverse(root.right, list);
    }

    // https://leetcode.com/submissions/detail/176344614/
    public void recoverTree(TreeNode root) {
        Pair<TreeNode, TreeNode> errorPair1 = new Pair<>(null, null);
        Pair<TreeNode, TreeNode> errorPair2 = new Pair<>(null, null);

        traverseForErrors(root, errorPair1, errorPair2);

        if (errorPair2.first == null) {
            int temp = errorPair1.first.val;
            errorPair1.first.val = errorPair1.second.val;
            errorPair1.second.val = temp;
        } else {
            int temp = errorPair1.first.val;
            errorPair1.first.val = errorPair2.second.val;
            errorPair2.second.val = temp;
        }
    }

    TreeNode mPrevious;
    void traverseForErrors(TreeNode current, Pair<TreeNode, TreeNode> errorPair1, Pair<TreeNode, TreeNode> errorPair2) {
        if (current == null) {
            return;
        }

        traverseForErrors(current.left, errorPair1, errorPair2);

        if (mPrevious != null && mPrevious.val > current.val) {
            if (errorPair1.first == null) {
                errorPair1.first = mPrevious;
                errorPair1.second = current;
            } else {
                errorPair2.first = mPrevious;
                errorPair2.second = current;
            }
        }
        mPrevious = current;

        traverseForErrors(current.right, errorPair1, errorPair2);
    }

    static class Pair<A, B> {
        public A first;
        public B second;
        public Pair(A a, B b) {
            first = a;
            second = b;
        }
    }

}