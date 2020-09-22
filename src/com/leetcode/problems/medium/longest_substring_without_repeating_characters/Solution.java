package com.leetcode.problems.medium.longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/submissions/detail/173222761/ beats 35%. now beats ~77%
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> uniqsWithIndex = new HashMap<>();
        int i=0, j=1, sLen=s.length();
        if (sLen < 1) return 0;
        uniqsWithIndex.put(s.charAt(0), 0);
        int max=1;

        while (j<sLen) {
            char c = s.charAt(j);
            if (uniqsWithIndex.containsKey(c)) {
                int nextUniqueStart = uniqsWithIndex.get(c)+1;
                while (i<nextUniqueStart) {
                    uniqsWithIndex.remove(s.charAt(i));
                    i++; // moves i up to one after the (previously known) repeated char's index, so that we can assume everything after i now is all unique.
                }
            }

            uniqsWithIndex.put(c,j); // updates index
            max = Math.max(uniqsWithIndex.size(), max);
            j++;
        }

        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        int result = 1;
        for (int i=0; i<s.length(); i++) {
            int temp = 1;
            int j=i+1;
            // System.out.println("i is " + i + "  " + s.charAt(i));
            boolean[] seenChars = new boolean[256]; // assume ascii
            while (j < s.length() && s.charAt(j) != s.charAt(i) && !seenChars[(int)s.charAt(j)]) {
                // System.out.println("j is " + j + "  " + s.charAt(j) + "  and temp is " + temp);
                seenChars[(int)s.charAt(j)] = true;
                temp++;
                j++;
            }

            if (temp > result) {
                result = temp;
            }
        }

        return result;
    }
}
// abcdefcgaxyz123456
