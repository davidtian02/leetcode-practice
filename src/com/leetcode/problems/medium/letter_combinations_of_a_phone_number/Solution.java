package com.leetcode.problems.medium.letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    // runtime O(3.2 ^ N) where N is number of chars in digits.
    // space complexity? O(3.2 ^ N) where N is number of chars in digits.
    // edges: null? same number? 2-9?
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        Set<String> next = new HashSet<>();
        next.add("");
        combinations(result, digits, 0, next);
        return result;
    }

    private void combinations(List<String> result, String digits, int index, Set<String> current) {
        if (index == digits.length()) {
            result.addAll(current);
            return;
        }
        char[] letters = getLetters(digits.charAt(index));
        Set<String> copy = new HashSet<>();
        for (char letter : letters) {
            for (String c : current) {
                copy.add(c + letter);
            }
        }

        combinations(result, digits, index+1, copy);
    }

    private char[] getLetters(char c) {
        switch(c) {
            case '2': return new char[]{'a', 'b', 'c'};
            case '3': return new char[]{'d', 'e', 'f'};
            case '4': return new char[]{'g', 'h', 'i'};
            case '5': return new char[]{'j', 'k', 'l'};
            case '6': return new char[]{'m', 'n', 'o'};
            case '7': return new char[]{'p', 'q', 'r', 's'};
            case '8': return new char[]{'t', 'u', 'v'};
            case '9': return new char[]{'w', 'x', 'y', 'z'};
        }
        return null;
    }
}
