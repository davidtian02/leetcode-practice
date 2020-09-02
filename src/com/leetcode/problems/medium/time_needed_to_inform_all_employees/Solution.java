package com.leetcode.problems.medium.time_needed_to_inform_all_employees;

import java.util.*;

// https://leetcode.com/problems/time-needed-to-inform-all-employees/submissions/
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n < 1) {
            return 0;
        }
        // create hash map mapping of all relations, with root ^
        Node root = generateTree(headID, manager, informTime);
        // use root to traverse to leaf, and find max amongst leaves
        Node holderNode = new Node(-2, -1); // purely used to hold the answer
        findMax(root, holderNode);
        return holderNode.time;
    }

    private Node generateTree(int headID, int[] manager, int[] informTime) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i=0; i<manager.length; i++) {
            final Node employee;
            final Node directManager;
            int managerId = manager[i];
            if (!map.containsKey(i)) {
                employee = new Node(i, informTime[i]);
                map.put(i, employee);
            } else {
                employee = map.get(i);
            }
            // build relationship
            if (!map.containsKey(managerId)) {
                directManager = new Node(managerId, managerId == -1 ? informTime[headID] : informTime[managerId]);
                map.put(managerId, directManager);
            } else {
                directManager = map.get(managerId);
            }

            directManager.children.add(employee);
        }
        return map.get(headID);
    }

    private void findMax(Node root, Node holderNode) {
        if (root.children.isEmpty()) {
            holderNode.time = Math.max(root.time, holderNode.time);
        }
        for (Node c : root.children) {
            c.time += root.time;
            findMax(c, holderNode);
        }
    }

    static class Node {
        int id, time;
        Set<Node> children;
        Node(int i, int t) {
            id = i;
            time = t;
            children = new HashSet<>();
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.numOfMinutes(11, 4, new int[]{5, 9, 6, 10, -1, 8, 9, 1, 9, 3, 4}, new int[]{0, 213, 0, 253, 686, 170, 975, 0, 261, 309, 337}));
    }
}