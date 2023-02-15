package com.leetcode.problems.easy.excel_sheet_column_title;

class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            int digit;
            int carry;
            if (columnNumber % 26 == 0) {
                digit = 26;
                carry = 1;
            } else {
                digit = columnNumber % 26;
                carry = 0;
            }

            char c = (char)('A' - 1 + digit);
            sb.append(c);
            columnNumber /= 26;
            columnNumber -= carry;
        }

        return sb.reverse().toString();
    }
}
