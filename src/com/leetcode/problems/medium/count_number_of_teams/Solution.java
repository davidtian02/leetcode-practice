package com.leetcode.problems.medium.count_number_of_teams;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public int numTeams(int[] rating) {
        int count = 0;
        for (int i=0; i<rating.length; i++) {
            int lessThanOnLeft = 0;
            int greatThanOnRight = 0;

            int greatThanOnLeft = 0;
            int lessThanOnRight = 0;
            for (int j=i-1; j>=0; j--) {
                if (rating[j] < rating[i]) {
                    lessThanOnLeft++;
                } else {
                    greatThanOnLeft++;
                }
            }
            for (int k=i+1; k<rating.length; k++) {
                if (rating[k] > rating[i]) {
                    greatThanOnRight++;
                } else {
                    lessThanOnRight++;
                }
            }

            count += lessThanOnLeft * greatThanOnRight; // combination
            count += greatThanOnLeft * lessThanOnRight;
        }
        return count;
    }

    // 5:23
    // 5:44
    public int numTeams2(int[] rating) {
        int forward = countTeams(rating);
        reverse(rating);
        int backward = countTeams(rating);
        return forward + backward;
    }

    private int countTeams(int[] rating) {
        List<Integer> biggerThanMe[] = new LinkedList[rating.length];
        for (int i=0; i<rating.length; i++) {
            int me = rating[i];
            for (int j=i+1; j<rating.length; j++) {
                if (rating[j] > me) {
                    if (biggerThanMe[i] == null) {
                        biggerThanMe[i] = new LinkedList<>();
                    }
                    biggerThanMe[i].add(j);
                }
            }
        }

        int count = 0;

        for (int i=0; i<rating.length; i++) {
            if (biggerThanMe[i] != null) {
                for (int j : biggerThanMe[i]) {
                    if (biggerThanMe[j] != null) {
                        count += biggerThanMe[j].size();
                    }
                }
            }
        }

        return count;
    }

    private void reverse(int[] rating) {
        for (int i=0; i<rating.length/2; i++) {
            int temp = rating[i];
            rating[i] = rating[rating.length - 1 - i];
            rating[rating.length - 1 - i] = temp;
        }
    }
}