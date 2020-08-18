package com.leetcode.challenge.august2020.distribute_candies_to_people;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/551/week-3-august-15th-august-21st/3427/
class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] arr = new int[num_people];
        // first, find how many levels.
        int sig = sigma(num_people);
        if (sig >= candies) {
            fillRemaining(arr, candies, 1);
            return arr;
        }

        int sum = sig; // after first row
        int rows = 0;
        int prevSum = 0;
        while (sum < candies) {
            prevSum = sum;
            rows++;
            sum = sigma((rows+1)*num_people);
        }
        int remainingCandies = candies - prevSum;
        int rightmostElement = sigma(rows) * num_people;

        for (int i=arr.length-1; i>=0; i--) {
            arr[i] = rightmostElement;
            rightmostElement -= rows;
        }

        // fill remainder of candies
        int next = rows*arr.length + 1;
        fillRemaining(arr, remainingCandies, next);

        return arr;
    }

    private void fillRemaining(int arr[], int remainingCandies, int offset) {
        for (int i=0; i<arr.length; i++) {
            if (remainingCandies>offset) {
                arr[i] += offset;
                remainingCandies-=offset;
                offset++;
            } else {
                arr[i] += remainingCandies;
                break;
            }
        }
    }

    private int sigma(int n) {
        if ((n&0x01)==1) {
            int mid = (n>>1)+1;
            return (mid<<1)*(mid-1)+mid;
        } else {
            int mid = (n>>1);
            return (n+1)*(mid);
        }
    }
}