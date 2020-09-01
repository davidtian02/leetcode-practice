package com.leetcode.challenge.august2020.delete_node_in_a_bst;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/553/week-5-august-29th-august-31st/3443/
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // else will need to actually pick a val
            removeLeftmostOfRightSubtree(root);
            return root;
        }

        updateVals(root, key);

        return root;
    }

    // we replace 'found' with the value of the leftmost node of the right subtree, and then delete that node
    private TreeNode updateVals(TreeNode root, int key) {
        TreeNode[] parentChild = new TreeNode[2];
        findNodes(null, root, key, parentChild);
        TreeNode parent = parentChild[0];
        TreeNode child = parentChild[1];
        if (child == null) {
            // not found
            return null;
        }

        if (child.left == null && child.right == null) {
            if (parent.right == child) {
                parent.right = null;
            } else {
                parent.left = null;
            }
        } else if (child.left == null) {
            if (parent.right == child) {
                parent.right = child.right;
            } else {
                parent.left = child.right;
            }
        } else if (child.right == null) {
            if (parent.right == child) {
                parent.right = child.left;
            } else {
                parent.left = child.left;
            }
        } else {
            removeLeftmostOfRightSubtree(child);
        }

        return root;
    }

    // we know at this point that root has both children in tact
    private void removeLeftmostOfRightSubtree(TreeNode root) {
        TreeNode rightSubtreeRoot = root.right;
        TreeNode leftParent = rightSubtreeRoot;

        if (leftParent.left == null) {
            root.val = leftParent.val;
            root.right = leftParent.right;
            return;
        }

        TreeNode leftChild = leftParent.left;
        while (leftChild.left != null) {
            leftParent = leftChild;
            leftChild = leftChild.left;
        }

        leftParent.left = leftChild.right;
        root.val = leftChild.val;
    }

    private void findNodes(TreeNode parent, TreeNode current, int key, TreeNode[] parentChild) {
        if (current == null) {
            return;
        }

        if (current.val == key) {
            parentChild[0] = parent;
            parentChild[1] = current;
        } else if (current.val < key) {
            findNodes(current, current.right, key, parentChild);
        } else {
            findNodes(current, current.left, key, parentChild);
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

    public static void main(String... args) {
        Solution runner = new Solution();

//        test1(runner);
        test2(runner);
    }

    private static void test2(Solution runner) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);
        runner.deleteNode(root, 3);
    }

    private static void test1(Solution runner) {
        TreeNode r = new TreeNode(5);
        r.left = new TreeNode(3);
        r.left.left = new TreeNode(2);
        r.left.right = new TreeNode(4);
        r.right = new TreeNode(6);
        r.right.right = new TreeNode(7);
        TreeNode result = runner.deleteNode(r, 3);
    }
}
