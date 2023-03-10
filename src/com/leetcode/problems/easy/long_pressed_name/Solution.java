package com.leetcode.problems.easy.long_pressed_name;

class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int i=0, j=0;
        while (i<name.length() && j<typed.length()) {
            char n = name.charAt(i);
            char t = typed.charAt(j);

            int nCount = 1;
            int tCount = 1;
            if (n != t) {
                return false;
            }

            while (i+1 < name.length() && n == name.charAt(i+1)) {
                nCount++;
                i++;
            }

            while (j+1 < typed.length() && t == typed.charAt(j+1)) {
                tCount++;
                j++;
            }

            if (tCount < nCount) {
                return false;
            }

            i++;
            j++;
        }

        return i == name.length() && j == typed.length();
    }

    public boolean isLongPressedName2(String name, String typed) {
        if (typed.length() < name.length()) {
            return false;
        }

        int i=1, j=1;
        char prev = name.charAt(0);
        if (prev != typed.charAt(0)) return false;

        while (i<name.length()) {
            char n = name.charAt(i);
            if (j == typed.length()) {
                return false;
            } else {
                if (n != typed.charAt(j)) {
                    while(j < typed.length() && typed.charAt(j) == prev) {
                        // System.out.println("checking: " + typed.charAt(j));
                        j++;
                    }
                }

                // System.out.println("i, j: " + i + ", " + j + ", " + n + ", " + typed.charAt(j));
                if (j == typed.length() || n != typed.charAt(j)) {
                    return false;
                }


            }

            prev = n;

            i++;
            j++;
        }

        while(j < typed.length()) {
            if (typed.charAt(j) != prev) {
                return false;
            }
            j++;
        }

        return true;
    }
}
