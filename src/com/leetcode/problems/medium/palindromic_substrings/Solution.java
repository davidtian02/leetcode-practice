package com.leetcode.problems.medium.palindromic_substrings;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/palindromic-substrings/description/
class Solution {
    // 2:40. finished 3:03
    List<String> palindromes = new LinkedList<>();
    int globalCount = 0;
    public int countSubstrings(String s) {
        for (int i=0; i<s.length(); i++) {
            middleExpansion(s, i);
            middleExpansionGhostChar(s, i, i+1);
        }

        return globalCount;
    }

    private void middleExpansion(String s, int mid) {
        // middle expansion revolving real char
        int i=0;
        int len = s.length();
        while (mid-i >= 0 && mid+i < len) {
            if (s.charAt(mid-i) == s.charAt(mid+i)) {
                globalCount++;
                i++;
            } else {
                return;
            }
        }
    }

    private void middleExpansionGhostChar(String s, int midL, int midR) {
        int len = s.length();
        while(midL >= 0 && midR < len) {
            if (s.charAt(midL) == s.charAt(midR)) {
                globalCount++;
                midL--;
                midR++;
            } else {
                return;
            }
        }
    }
}