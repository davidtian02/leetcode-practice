package com.leetcode.longestSubstringWithoutRepeatingCharacters;

// https://leetcode.com/submissions/detail/173222761/ beats 35%
class Solution {
    public int lengthOfLongestSubstring(String s) {
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
