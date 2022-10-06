package com.leetcode.problems.medium.count_and_say;

class Solution {
    public String countAndSay(int n) {
        String res = null;
        for (int i=1; i<=n; i++) {
            res = helper(i, res);
        }
        return res;
    }

    private String helper(int n, String current) {
        if (n == 1) {
            return "1";
        }

        int multiplier = 1;
        Character previous = null;
        StringBuilder next = new StringBuilder();
        char c = '.';
        for (int i=0; i<current.length(); i++) {
            c = current.charAt(i);
            if (previous == null) {
                previous = c;
                continue;
            } else {
                if (previous != c) {
                    next.append("" + multiplier + "" + previous);
                    multiplier = 1;
                } else {
                    multiplier++;
                }
            }
            previous = c;
        }
        next.append("" + multiplier + "" + c);

        return next.toString();
    }
}

// 1
// 11
// 21
// 1211
// 111221
// 312211
// 13112221
// 1113213211
// 31131211131221