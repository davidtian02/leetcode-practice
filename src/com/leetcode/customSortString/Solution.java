package com.leetcode.customSortString;

//https://leetcode.com/problems/custom-sort-string/description/
class Solution {
    // 4:00
    public String customSortString(String S, String T) {
        char[] sortedChars = new char[26];
        for (int i=0; i<S.length(); i++) {
            sortedChars[i] = S.charAt(i);
        }

        // System.out.println(Arrays.toString(sortedChars));

        StringBuilder[] freq = new StringBuilder[26];
        char c;
        int index;
        for (int i=0; i<T.length(); i++) {
            c = T.charAt(i);
            index = c-'a';
            if (freq[index] == null) {
                freq[index] = new StringBuilder("" + c);
            } else {
                freq[index].append(c);
            }
        }

        // System.out.println(Arrays.toString(freq));

        StringBuilder result = new StringBuilder();
        for (int i=0; i<S.length(); i++) {
            c = S.charAt(i);
            index = c - 'a';
            if (freq[index] != null) {
                result.append(freq[index]);
                freq[index] = null;
            }
        }

        // System.out.println(result);

        for (int i=0; i<freq.length; i++) {
            if (freq[i] != null) {
                result.append(freq[i]);
            }
        }

        return result.toString();
    }
}