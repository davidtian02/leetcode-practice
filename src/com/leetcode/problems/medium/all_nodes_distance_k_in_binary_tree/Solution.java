package com.leetcode.problems.medium.all_nodes_distance_k_in_binary_tree;

import java.util.*;

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
    // TODO actually don't need to create hashmap
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, GraphNode> mapping = constructMapping(root);
        GraphNode node = mapping.get(target);
        // System.out.println("mapping?" + mapping);

        Set<GraphNode> destinations = travel(node, k);

        // System.out.println("destinations?" + destinations);

        List<Integer> results = new LinkedList<>();
        for (GraphNode gn : destinations) {
            results.add(gn.val);
        }
        return results;
    }

    static class GraphNode {
        int val;
        Set<GraphNode> neighbors;
        GraphNode(TreeNode tn) {
            val = tn.val;
            neighbors = new HashSet<>();
        }
        @Override
        public String toString() {
            return "" + val;
        }
    }

    private Map<TreeNode, GraphNode> constructMapping(TreeNode root) {
        Map<TreeNode, GraphNode> mapping = new HashMap<>();

        Stack<TreeNode> dfs = new Stack<>();
        GraphNode gn = new GraphNode(root);
        dfs.push(root);
        mapping.put(root, gn);
        while(!dfs.isEmpty()) {
            TreeNode tn = dfs.pop();
            GraphNode parent = mapping.get(tn);
            if (tn.left != null) {
                GraphNode left = new GraphNode(tn.left);
                mapping.put(tn.left, left);

                parent.neighbors.add(left);
                left.neighbors.add(parent);

                dfs.push(tn.left);
            }
            if (tn.right != null) {
                GraphNode right = new GraphNode(tn.right);
                mapping.put(tn.right, right);

                parent.neighbors.add(right);
                right.neighbors.add(parent);

                dfs.push(tn.right);
            }
        }

        return mapping;
    }

    private Set<GraphNode> travel(GraphNode node, int distance) {
        Set<GraphNode> destinations = new HashSet<>();
        Set<GraphNode> visited = new HashSet<>();

        Set<GraphNode> previous = new HashSet<>();
        previous.add(node);
        visited.add(node);
        if (distance == 0) {
            return previous;
        }

        while(!previous.isEmpty() && distance > 0) {
            destinations = new HashSet<>();
            Iterator<GraphNode> iter = previous.iterator();
            // System.out.println("=====on level=====");
            while (iter.hasNext()) {
                GraphNode p = iter.next();
                visited.add(p);
                // System.out.println("at node: " + p.val);
                for (GraphNode pn : p.neighbors) {
                    if (!visited.contains(pn)) {
                        destinations.add(pn);
                    }
                }
                // System.out.println("added next destinations: " + destinations);
            }

            previous = destinations;
            distance--;
        }

        return destinations;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}