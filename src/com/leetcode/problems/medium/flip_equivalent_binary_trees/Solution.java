package com.leetcode.problems.medium.flip_equivalent_binary_trees;

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
    // 5:10 - 5:59 (could be cleaned up a lot. but hey beats 100%)
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // System.out.println("checking nodes: " + root1 + ", " + root2 + ": " + (root1 == null?"null":""+root1.val) + ", " + (root2==null?"null":root2.val));

        if (root1 != null && root2 != null) {
            if (root1.val != root2.val) {
                return false;
            }

            if (!areChildrenFlippable(root1, root2)) {
                // System.out.println("children were not flippable, so ending early");
                return false;
            }

            return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
        } else if (root1 != null || root2 != null) {
            // System.out.println("one of the root was not nil");
            return false;
        } else {
            return true;
        }
    }

    // not null
    private boolean areChildrenFlippable(TreeNode root1, TreeNode root2) {
        if (root1.left == null && root1.right == null) {
            if (root2.left != null || root2.right != null) {
                return false;
            }
        }

        if (root1.left == null) {
            if (root2.left == null) { // mirror, not flipped
                if (root1.right == null && root2.right == null) {
                    return true;
                } else if ((root1.right != null && root2.right == null) || (root1.right == null && root2.right != null)){
                    return false;
                } else {
                    if (root1.right.val != root2.right.val) {
                        return false; // nor okay if value not same
                    } else {
                        return true;
                    }
                }
            } else if (root2.right == null) { // flipped
                if (root1.right == null && root2.left == null) {
                    return true;
                } else if ((root1.right != null && root2.left == null) || (root1.right == null && root2.left != null)){
                    return false;
                } else {
                    if (root1.right.val != root2.left.val) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }

        if (root1.right == null) {
            if (root2.right == null) { // mirror, not flipped
                if (root1.left == null && root2.left == null) {
                    return true;
                } else if ((root1.left != null && root2.left == null) || (root1.left == null && root2.left != null)){
                    return false;
                } else {
                    if (root1.left.val != root2.left.val) {
                        return false; // nor okay if value not same
                    } else {
                        return true;
                    }
                }
            } else if (root2.left == null) { // flipped
                if (root1.left == null && root2.right == null) {
                    return true;
                } else if ((root1.left != null && root2.right == null) || (root1.left == null && root2.right != null)){
                    return false;
                } else {
                    if (root1.left.val != root2.right.val) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }

        if (root2.left == null || root2.right == null) {
            return false;
        }

        // purely check values:
        if (root1.left.val == root2.left.val) {
            return root1.right.val == root2.right.val;
        }

        if (root1.left.val == root2.right.val) {
            return root1.right.val == root2.left.val;
        }

        return false;
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
