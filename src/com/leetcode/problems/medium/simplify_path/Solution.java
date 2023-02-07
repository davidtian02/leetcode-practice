package com.leetcode.problems.medium.simplify_path;

import java.util.LinkedList;

class Solution {
    // 3:25?
    public String simplifyPath(String path) {
        String[] tokens = path.split("/");

        LinkedList<String> nodes = new LinkedList<>();
        for (String t: tokens) {
            if (!t.isEmpty() && !".".equals(t)) {
                if ("..".equals(t)) {
                    nodes.pollLast();
                } else {
                    nodes.add(t);
                }
            }
        }

        return "/" + String.join("/", nodes);
    }
}
