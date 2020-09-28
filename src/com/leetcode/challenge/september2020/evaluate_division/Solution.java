package com.leetcode.challenge.september2020.evaluate_division;

import java.util.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        final Map<String, Node> graph = constructGraph(equations, values);

        return queries.stream().mapToDouble( (query) -> {
            return deduce(query, graph); // note, if same then return 1
        }).toArray();
    }

    private double deduce(List<String> query, Map<String, Node> graph) {
        String n = query.get(0);
        String d = query.get(1);
        if (!graph.containsKey(n) || !graph.containsKey(d)) {
            return -1;
        } else if (n.equals(d)) {
            return 1;
        }

        Node numer = graph.get(n);
        Node denom = graph.get(d);
        Set<Node> visited = new HashSet<>();
        visited.add(denom);
        return dfs(denom, numer, 1, visited);
    }

    private double dfs(Node current, Node target, double currentCost, Set<Node> visited) {
        if (target == current) {
            return currentCost;
        }

        for (Map.Entry<Node, Double> child : current.children.entrySet()) {
            Node c = child.getKey();
            if (!visited.contains(c)) {
                visited.add(c);
                double val = dfs(c, target, currentCost * child.getValue(), visited);
                if (val != -1) {
                    return val;
                }
                visited.remove(c);
            }
        }

        return -1;
    }

    private Map<String, Node> constructGraph(List<List<String>> equations, double[] values) {
        Map<String, Node> nodes = new HashMap<>();
        for (int i=0; i<equations.size(); i++) {
            List<String> eq = equations.get(i);
            double val = values[i];
            String n = eq.get(0);
            String d = eq.get(1);
            final Node numer;
            final Node denom;
            if (!nodes.containsKey(n)) {
                numer = new Node();
                nodes.put(n, numer);
            } else {
                numer = nodes.get(n);
            }
            if (!nodes.containsKey(d)) {
                denom = new Node();
                nodes.put(d, denom);
            } else {
                denom = nodes.get(d);
            }

            numer.addChild(denom, 1/val);
            denom.addChild(numer, val);
        }
        return nodes;
    }


    static class Node {
        Map<Node, Double> children = new HashMap<>(); // represents costs to get to another node
        void addChild(Node c, double val) {
            children.put(c, val);
        }
    }

    // we actually have to construct a graph for how to get from 1 point to another valu.
}
