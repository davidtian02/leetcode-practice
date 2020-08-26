package com.leetcode.problems.easy.fizz_buzz;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3437/
class Solution {

    public List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<>();
        for (int i=1; i<=n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else {
                result.add("" + i);
            }
        }
        return result;
    }


    // this doesn't perform much better
    public List<String> fizzBuzz2(int n) {
        List<String> result = new LinkedList<>();
        int a = 3, b = 5, c = 15;
        for (int i=1; i<=n; i++) {
            if (i == c) {
                result.add("FizzBuzz");
                c += 15;
                a += 3;
                b += 5;
            } else if (i == b) {
                result.add("Buzz");
                b += 5;
            } else if (i == a) {
                result.add("Fizz");
                a += 3;
            } else {
                result.add("" + i);
            }
        }

        return result;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.fizzBuzz(3));
    }
}
