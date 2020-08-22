package com.leetcode.challenge.august2020.h_index;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/550/week-2-august-8th-august-14th/3420/
class Solution {
    public int hIndex(int[] citations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int c : citations) {
            pq.add(c);
        }
        int h = citations.length;
        int index = 0;
        while (index<citations.length) {
            int c = pq.remove();
            if (c < index+1){
                return index;
            }
            index++;
        }
        return h;
    }

    public int hIndex2(int[] citations) {
        List<Integer> citationsSorted = Arrays.stream(citations).boxed().collect(Collectors.toList());
        citationsSorted.sort(Comparator.reverseOrder());
        int h = citations.length;
        for (int i=0; i<citations.length; i++) {
            int c = citationsSorted.get(i);
            if (c < i+1) {
                h = i; // no need to plus one here
                break;
            }
        }
        return h;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.hIndex(new int[]{1,3}));
        System.out.println(runner.hIndex(new int[]{2,4}));
    }
}
