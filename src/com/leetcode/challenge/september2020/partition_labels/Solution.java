package com.leetcode.challenge.september2020.partition_labels;

import java.util.*;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3448/
class Solution {
    public List<Integer> partitionLabels(String S) {
        // first put into frequency map for all chars
        Map<Character, Integer> freq = createFrequencyMap(S);

        // then go through each index, holding bag of chars. if bag is ever empty, it formed a substring.
        return groupChars(S, freq);
    }

    private List<Integer> groupChars(String str, Map<Character, Integer> freq) {
        List<Integer> result = new LinkedList<>();
        Set<Character> bag = new HashSet<>();
        int count = 1;
        for (int i=0, len=str.length(); i<len; i++) {
            char c = str.charAt(i);
            int f = freq.get(c) - 1;
            if (f == 0) {
                bag.remove(c);
            } else {
                bag.add(c);
            }
            freq.put(c, f);
            if (bag.isEmpty()) {
                result.add(count);
                count = 0;
            }
            count++;
        }
        // what about 1 element str?
        return result;
    }

    private Map<Character, Integer> createFrequencyMap(String str) {
        Map<Character, Integer> freq = new HashMap<>();
        str.chars().forEach(c -> freq.put((char) c, (freq.containsKey((char)c) ? freq.get((char)c)+1: 1)));
        return freq;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(runner.partitionLabels("abcddd"));
    }
}
