package com.leetcode.challenge.august2020.excel_sheet_column_number;

class Solution {
    public int titleToNumber(String s) {
        int len = s.length();
        long res = 0;
        for (int i=0; i<len; i++) {
            int temp = s.charAt(i) - 'A' + 1;
            res += temp * Math.pow((long) 26,(long)len-i-1);
        }
        return (int) res;
    }
}