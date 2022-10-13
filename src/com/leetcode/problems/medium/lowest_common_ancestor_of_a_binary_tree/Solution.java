package com.leetcode.problems.medium.lowest_common_ancestor_of_a_binary_tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pAncestors = new LinkedList<>(); // because unique values of nodes
        List<TreeNode> qAncestors = new LinkedList<>();

        findAncestors(root, p.val, pAncestors);
        Set<Integer> pAncestorsSet = convert(pAncestors);
        findAncestors(root, q.val, qAncestors);

        for (TreeNode node : qAncestors) {
            if (pAncestorsSet.contains(node.val)) {
                return node;
            }
        }

        return null; // should never hit here
    }

    private boolean findAncestors(TreeNode root, int val, List<TreeNode> ancestors) {
        if (root == null) {
            return false;
        }

        boolean foundInLeft = findAncestors(root.left, val, ancestors);
        if (foundInLeft || root.val == val) {
            ancestors.add(root);
            return true;
        }

        boolean foundInRight = findAncestors(root.right, val, ancestors);
        if (foundInRight) {
            ancestors.add(root);
            return true;
        }

        return false;
    }

    private Set<Integer> convert(List<TreeNode> list) {
        Set<Integer> set = new HashSet<>();
        for (TreeNode node : list) {
            set.add(node.val);
        }
        return set;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
