package com.leetcode.challenge.september2020.sequential_digits;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/556/week-3-september-15th-september-21st/3465/
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        return generateAllInRange(numDigits(low), numDigits(high), low, high);
    }

    private int numDigits(int val) {
        int numDigits = 0;
        while (val > 0) {
            val /= 10;
            numDigits++;
        }
        return numDigits;
    }

    private List<Integer> generateAllInRange(int d1, int d2, int low, int high) {
        List<Integer> list = new LinkedList<>();
        for (int i=d1; i<=d2; i++) {
            generateSequenceForNumDigits(list, i, low, high);
        }
        return list;
    }

    private List<Integer> generateSequenceForNumDigits(List<Integer> result, int num, int low, int high) {
        if (num > 9) {
            return result;
        }
        int count = 0;
        int bumper = 0;
        while (count < num) {
            bumper = (bumper * 10) + 1;
            count++;
        }

        int startSeq = 0;
        count = 0;
        while (count < num) {
            count++;
            startSeq = (startSeq*10) + count;
        }

        int temp = startSeq;
        if (temp >= low && temp <= high) {
            result.add(temp);
        }
        while ((temp+bumper)%10 != 0) {
            temp += bumper;
            if (temp >= low && temp <= high) {
                result.add(temp);
            }
        }
        return result;
    }
}

// let's say from 1 -> 1000000
// from 1 -> 9, 1,2,3,4... 9
// 1->11 : 1,2,3.... 9
// 1->100: 1,2,.... 9, 12,23,34,45,56...89,
// 1->1000:1,2,...9, 123,234,345,... 789
// 56,1000: 56, 67, 78, 89, 123,234,... 789, 1234,2345,...6789,
// start lowest val, add "11" , then 111, then 1111, until upper range bound.
// lowest val: min when d = 2? 3?, keep searching until.
