package com.leetcode.problems.medium.longest_substring_with_at_least_k_repeating_characters;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Solution {
    int globalMax = 0;
    public int longestSubstring(String s, int k) {
        Set<Character> breakingChars = findBreakingCharacters(s, k);
        String regex = buildRegex(breakingChars);
        if (regex.equals("")) {
            return s.length();
        }
        findLongest(s.split(regex), k);
        return globalMax;
    }

    private void findLongest(String[] tokens, int k) {
        for (String token : tokens) {
            if (token.length() < k) continue;

            Set<Character> breakingChars = findBreakingCharacters(token, k);
            String regex = buildRegex(breakingChars);
            if (regex.equals("")) {
                if (globalMax < token.length()) {
                    globalMax = token.length();
                    continue;
                }
            }

            findLongest(token.split(regex), k);
        }
    }

    private String buildRegex(Set<Character> chars) {
        if (chars.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator<Character> iter = chars.iterator();
            char c = iter.next();
            sb.append(c);
            while (iter.hasNext()) {
                c = iter.next();
                sb.append("|" + c);
            }
            return sb.toString();
        }
    }

    private Set<Character> findBreakingCharacters(String s, int k) {
        int[] freq = new int[26];
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            freq[((int) c - 'a')]++;
        }

        Set<Character> set = new HashSet<>();
        for (int i=0; i<freq.length; i++) {
            if (freq[i] > 0 && freq[i] < k) {
                set.add((char)('a' + i));
            }
        }

        return set;
    }
}