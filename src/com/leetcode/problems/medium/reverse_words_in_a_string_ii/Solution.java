package com.leetcode.problems.medium.reverse_words_in_a_string_ii;

// https://leetcode.com/problems/reverse-words-in-a-string-ii/
class Solution {
    public void reverseWords(char[] s) {
        if (s.length < 1) return;
        reverseSequence(s, 0, s.length-1);
        reverseWordsOnly(s);
    }

    private void reverseSequence(char[] s, int left, int right) {
        while (left < right) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }
    }

    private void reverseWordsOnly(char[] s) {
        int left = 0;
        int right = 0;
        while (right < s.length) {
            // go to first space
            while (right < s.length && s[right] != ' ') {
                right++;
            }
            reverseSequence(s, left, right-1);
            right++;
            left = right;
        }
    }

    public void reverseWords2(char[] s) {
        char[] output = new char[s.length];
        int outputIndex = output.length-1;
        int left=0, right=0;
        while (right < s.length) {
            // look for first space
            while (right < s.length && s[right] != ' ') {
                right++;
            }
            // copy word over
            int k = right-1;
            while (k >= left) {
                char ck = s[k];
                output[outputIndex] = ck;
                k--;
                outputIndex--;
            }

            // copy space
            if (right != s.length) output[outputIndex--] = ' ';
            right++;
            left = right;
        }

        for (int i=0; i<s.length; i++) {
            s[i] = output[i];
        }
    }
}
