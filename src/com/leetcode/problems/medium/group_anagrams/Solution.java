package com.leetcode.problems.medium.group_anagrams;

import java.util.*;

// https://leetcode.com/problems/group-anagrams/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // group anagrams together in sorted lexigraphical(?) order
        Map<String, List<String>> result = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (!result.containsKey(sorted)) {
                result.put(sorted, new LinkedList<>());
            }
            result.get(sorted).add(s);
        }

        return new ArrayList<>(result.values());
    }
}
