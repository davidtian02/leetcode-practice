package com.leetcode.problems.medium.longest_palindromic_substring;

// https://leetcode.com/problems/longest-palindromic-substring/submissions/
// note - there's a ton of optimizations
class Solution {
    int minLeft, maxRight;
    public String longestPalindrome(String s) {
        int maxDistance = 0;
        minLeft = 0; maxRight = 0;
        for (int i=0,sLen=s.length(); i<sLen; i++) {
            findPalindrome(s, i);
        }
        return s.substring(minLeft, maxRight);
    }

    private void findPalindrome(String s, int index) {
        int left = index, right = index;
        findMaxPalindromeHelper(s, left, right);

        if (right+1 < s.length() && s.charAt(right) == s.charAt(right+1)) {
            findMaxPalindromeHelper(s, right, right+1);
        }
    }

    private void findMaxPalindromeHelper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // go back 1 since it was invalid to break the loop
        left++;
        right--;

        if ((1+right-left) > maxRight-minLeft) {
            maxRight = 1+right;
            minLeft = left;
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.longestPalindrome("a"));
        System.out.println(runner.longestPalindrome(""));
        System.out.println(runner.longestPalindrome("aaa"));
        System.out.println(runner.longestPalindrome("aba"));
        System.out.println(runner.longestPalindrome("abaca"));
        System.out.println(runner.longestPalindrome("abba"));
    }
}
