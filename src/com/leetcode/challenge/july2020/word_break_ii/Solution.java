package com.leetcode.challenge.july2020.word_break_ii;

import java.util.*;

// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/548/week-5-july-29th-july-31st/3406/
public class Solution {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<>(wordDict);
        Map<String, List<String>> cache = new HashMap<>();
        return wordBreakHelper(s, dictionary, cache);
    }

    private List<String> wordBreakHelper(String s, Set<String> dict, Map<String, List<String>> cache) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        List<String> result = new LinkedList<>();
        if (s.isEmpty()) {
            return result;
        }
        if (dict.contains(s)) {
            result.add(s);
        }

        for (int i=1; i<s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i, s.length());
            if (dict.contains(left)) {
                List<String> temp = wordBreakHelper(right, dict, cache);
                for (String t : temp) {
                    result.add(left + " " + t);
                }
            }
        }

        cache.put(s, result);
        return result;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.wordBreak("a", Arrays.asList("a", "b")));
        System.out.println(runner.wordBreak("ab", Arrays.asList("a", "b")));
        System.out.println(runner.wordBreak("abc", Arrays.asList("a", "b")));
        System.out.println(runner.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(runner.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(runner.wordBreak("abcd", Arrays.asList("a", "b", "c", "d", "pineapple")));
        List<String> actual = runner.wordBreak("aaaaaaa", Arrays.asList("aaaa", "aa", "a"));
        System.out.println(actual);
        Set<String> expectedSet = new HashSet<>(Arrays.asList("a a a a a a a", "aa a a a a a", "a aa a a a a", "a a aa a a a", "aa aa a a a", "aaaa a a a", "a a a aa a a", "aa a aa a a", "a aa aa a a", "a aaaa a a", "a a a a aa a", "aa a a aa a", "a aa a aa a", "a a aa aa a", "aa aa aa a", "aaaa aa a", "a a aaaa a", "aa aaaa a", "a a a a a aa", "aa a a a aa", "a aa a a aa", "a a aa a aa", "aa aa a aa", "aaaa a aa", "a a a aa aa", "aa a aa aa", "a aa aa aa", "a aaaa aa", "a a a aaaa", "aa a aaaa", "a aa aaaa"));
        System.out.println(new HashSet<>(actual).equals(expectedSet));
        System.out.println(runner.wordBreak("aaa", Arrays.asList("a", "aa")));
        System.out.println(runner.wordBreak("aaabaaa", Arrays.asList("a", "aa", "aaa")));
        System.out.println(runner.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
        System.out.println(runner.wordBreak("aaaaaaaa", Arrays.asList("aaaa","aaa","aa"))); // should contain "aaaa aa aa"
    }
}
