package com.leetcode.challenge.september2020.largest_time_for_given_digits;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3445/
class Solution {
    public String largestTimeFromDigits(int[] A) {
        List<Character> list = Arrays.stream(A).boxed().map(a -> (char)(a + '0')).collect(Collectors.toList());
        Set<String> combinations = new HashSet<>();
        generateCombinations(list, new StringBuilder(), combinations);
        String max = "";
        for (String s : combinations) {
            if (convertTime(s) >= 0) {
                if (isBigger(s, max)) {
                    max = s;
                }
            }
        }
        return max.isEmpty() ? max : max.substring(0, 2) + ":" + max.substring(2);
    }

    private boolean isBigger(String s, String max) {
        return convertTime(s) > convertTime(max);
    }

    private int convertTime(String s) {
        if (s.isEmpty()) return -1;
        String hr = s.substring(0, 2);
        String min = s.substring(2);
        int tHr = Integer.parseInt(hr);
        int tMin = Integer.parseInt(min);
        if (tHr > 23 || tMin > 59) {
            return -1;
        }
        return tHr * 60 + tMin;
    }

    private void generateCombinations(List<Character> remaining, StringBuilder current, Set<String> combos) {
        if (remaining.isEmpty()) {
            combos.add(current.toString());
            return;
        }
        for (int i=0; i<remaining.size(); i++) {
            List<Character> copy = new ArrayList<>(remaining);
            Character c = copy.remove(i);
            StringBuilder sb = new StringBuilder(current).append(c);
            generateCombinations(copy, sb, combos);
        }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.largestTimeFromDigits(new int[]{0,0,0,0}));
        System.out.println(runner.largestTimeFromDigits(new int[]{1,2,3,4}));
        System.out.println(runner.largestTimeFromDigits(new int[]{1,9,6,0}));
    }
}
