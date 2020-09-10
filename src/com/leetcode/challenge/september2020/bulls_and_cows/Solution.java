package com.leetcode.challenge.september2020.bulls_and_cows;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/555/week-2-september-8th-september-14th/3455/
class Solution {
    // to optimize, you could use char array of size 10 instead, since it's only chars.
    public String getHint(String secret, String guess) {
        Map<Character, Integer> bullsOtherChars = new HashMap<>();
        Map<Character, Integer> cowsOtherChars = new HashMap<>();
        int bulls = findBulls(secret, guess, bullsOtherChars, cowsOtherChars);
        int cows = findCows(bullsOtherChars, cowsOtherChars);
        return bulls + "A" + cows + "B";
    }

    private int findCows(Map<Character, Integer> bullsOtherChars, Map<Character, Integer> cowsOtherChars) {
        // loop to find differences
        int cows = 0;
        for (Map.Entry<Character, Integer> entry : cowsOtherChars.entrySet()) {
            char c = entry.getKey();
            int g = entry.getValue();
            if (bullsOtherChars.containsKey(c)) {
                cows += Math.min(g, bullsOtherChars.get(c));
            }
        }
        return cows;
    }

    private int findBulls(String secret, String guess, Map<Character, Integer> bullsOtherChars, Map<Character, Integer> cowsOtherChars) {
        // find direct hit
        int bulls = 0;
        for (int i=0,len=secret.length(); i<len; i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                bulls++;
            } else {
                if (bullsOtherChars.containsKey(s)) {
                    bullsOtherChars.put(s, bullsOtherChars.get(s) + 1);
                } else {
                    bullsOtherChars.put(s, 1);
                }

                if (cowsOtherChars.containsKey(g)) {
                    cowsOtherChars.put(g, cowsOtherChars.get(g) + 1);
                } else {
                    cowsOtherChars.put(g, 1);
                }
            }
        }
        return bulls;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.getHint("1807", "0871"));
        System.out.println(runner.getHint("1123", "0111"));
        System.out.println(runner.getHint("", ""));
        System.out.println(runner.getHint("11111", "00000"));
        System.out.println(runner.getHint("222", "222"));
    }
}
