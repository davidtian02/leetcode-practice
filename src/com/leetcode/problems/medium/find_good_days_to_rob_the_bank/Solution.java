package com.leetcode.problems.medium.find_good_days_to_rob_the_bank;

import java.util.LinkedList;
import java.util.List;

class Solution {
    // 4:30 - 4:55
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        boolean leftSide[] = findPriorDays(security, time);
        boolean rightSide[] = findAfterDays(security, time);

        // System.out.println(Arrays.toString(leftSide));
        // System.out.println(Arrays.toString(rightSide));

        List<Integer> result = new LinkedList<>();
        for (int i=0; i<security.length; i++) {
            if (leftSide[i] && rightSide[i] || time == 0) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean[] findPriorDays(int[] security, int time) {
        boolean[] before = new boolean[security.length];
        int decreasingCount = 0;
        before[0] = time==0? true : false;

        for (int i=1; i<security.length; i++) {
            if (security[i] <= security[i-1]) {
                decreasingCount++;

                if (decreasingCount >= time) {
                    before[i] = true;
                }
            } else {
                decreasingCount = 0;
            }
        }

        return before;
    }

    private boolean[] findAfterDays(int[] security, int time) {
        boolean[] after = new boolean[security.length];
        int increasingCount = 0;
        after[security.length-1] = time==0? true : false;

        for (int i=security.length-1; i>0; i--) {
            if (security[i] >= security[i-1]) {
                increasingCount++;

                if (increasingCount >= time) {
                    after[i-1] = true;
                }
            } else {
                increasingCount = 0;
            }
        }

        return after;
    }
}