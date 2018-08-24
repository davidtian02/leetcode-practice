package com.leetcode.validatenumber;

import java.util.regex.Pattern;

// got it tobe accepted: https://leetcode.com/submissions/detail/171455470/
// although... admittedly, this solution freaking sucks. it doesn't account for a lot of things, and doesn't use regex
// got it in 1 hour
class Solution {
    public boolean isNumber(String s) {
        if (isInteger(s)) {
            return true;
        }

        if (isFloat(s)) {
            return true;
        }

        if (isScientific(s)) {
            return true;
        }

        return false;
    }

    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
        } catch (Exception e) {
            return false;
        }

        // apparently ending in f is not acceptable
        return !s.endsWith("f") && !s.endsWith("F") && !s.endsWith("d") && !s.endsWith("D");
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            // it's possible that the number is just like "1,000"
            String temp = s.trim();
            String[] tokens = temp.split(",");
            if (tokens.length > 0) {
                if (tokens[0].length() > 3) {
                    return false;
                } else {
                    try {
                        Integer.parseInt(tokens[0]);
                    } catch (Exception e1) {
                        return false;
                    }
                }

                for (int i=1; i<tokens.length; i++) {
                    if (!tokens[i].matches("[0-9]{3}")) {
                        return false;
                    }
                }
            }

            return false;
        }

        return true;
    }

    private boolean isScientific(String s) {
        if (s.replaceAll("e", "").length() == s.length() - 1) {
            // if it only has 1 e
            String[] tokens = s.split("e");
            if (tokens.length == 2) {
                String regexLeadingWhitespace = "\\s+.*";
                String regexTrailingWhitespace = ".*\\s+";

                if (Pattern.matches(regexTrailingWhitespace, tokens[0]) || Pattern.matches(regexLeadingWhitespace, tokens[1])) {
                    return false;
                }

                return isFloat(tokens[0]) && isInteger(tokens[1]);
            }
        }

        return false;
    }
}