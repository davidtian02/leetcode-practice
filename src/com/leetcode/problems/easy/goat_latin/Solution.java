package com.leetcode.problems.easy.goat_latin;

// https://leetcode.com/problems/goat-latin/
class Solution {

    // TODO is stringbuilder faster to append char? or another stringbuilder?
    public String toGoatLatin(String S) {
        String[] tokens = S.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<tokens.length; i++) {
            sb.append(appliedRules(tokens[i]));
            sb.append("ma");
            for (int j=0; j<i+1; j++) {
                sb.append('a');
            }
            sb.append(' ');
        }
        if (tokens.length > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private StringBuilder appliedRules(String token) {
        StringBuilder result = new StringBuilder();
        char c = token.toLowerCase().charAt(0);
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                result = new StringBuilder(token);
                break;
            default:
                if (token.length() > 1) {
                    result = new StringBuilder(token.substring(1)).append(token.charAt(0)); // plus uses the stringbuilder anyways
                } else {
                    result = new StringBuilder(token);
                }
        }
        return result;
    }
}