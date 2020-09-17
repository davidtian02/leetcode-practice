package com.leetcode.companies.unofficial.microsoft.longest_semi_alternating_substring;

// https://leetcode.com/discuss/interview-question/398037/
class Solution {

    public int longestSubstring(String S) {
        if (S.length()<3) {
            return S.length();
        }

        int count = 2;
        int max = 2;
        for (int i=2,len=S.length(); i<len; i++) {
            char cPrevPrev=S.charAt(i-2), cPrev=S.charAt(i-1),c=S.charAt(i);
            if (c == cPrev && cPrev == cPrevPrev) {
                max = Math.max(max, count);
                count = 2;
            } else {
                count++;
            }
        }

        return Math.max(max,count);
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.longestSubstring("abbaabbaa"));
        System.out.println(runner.longestSubstring("abab"));
        System.out.println(runner.longestSubstring("abb"));
        System.out.println(runner.longestSubstring("bbb"));
        System.out.println(runner.longestSubstring("ab"));
        System.out.println(runner.longestSubstring(""));

        System.out.println(runner.longestSubstring("baaabbabbb"));
        System.out.println(runner.longestSubstring("babba"));
        System.out.println(runner.longestSubstring("abaaaa"));
    }
}
