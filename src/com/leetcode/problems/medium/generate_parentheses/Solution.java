package com.leetcode.problems.medium.generate_parentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// https://leetcode.com/problems/generate-parentheses/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        if (n < 1) {
            return results;
        }
        Set<StringBuilder> current = new HashSet<>();
        current.add(new StringBuilder("()"));
        for (int i=1; i<n; i++) {
            current = generateNextLevel(current);
        }
        return current.stream().map(StringBuilder::toString).collect(Collectors.toList());
    }

    private Set<StringBuilder> generateNextLevel(Set<StringBuilder> current) {
        Set<StringBuilder> result = new HashSet<>();
        for (StringBuilder sb : current) {
            // stick a new () at each index
            for (int i=0; i<sb.length(); i++) {
                StringBuilder copy = new StringBuilder(sb);
                copy.insert(i, "()");
                result.add(copy);
            }
        }
        return filterDuplicates(result);
    }

    private Set<StringBuilder> filterDuplicates(Set<StringBuilder> original) {
        return original.stream().map(StringBuilder::toString).collect(Collectors.toSet()).stream().map(StringBuilder::new).collect(Collectors.toSet());
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.generateParenthesis(5));
    }
}
