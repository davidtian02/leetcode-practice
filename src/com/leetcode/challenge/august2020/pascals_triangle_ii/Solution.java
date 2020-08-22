package com.leetcode.challenge.august2020.pascals_triangle_ii;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/550/week-2-august-8th-august-14th/3421/
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        for (int i=0; i<rowIndex; i++) {
            LinkedList<Integer> nextRow = new LinkedList<>();
            int prev = queue.removeFirst(); // start with 1, because no previous
            nextRow.add(prev);
            while (!queue.isEmpty()) {
                int removed = queue.removeFirst();
                nextRow.add(prev + removed);
                prev = removed;
            }
            nextRow.add(1); // ending 1
            queue = nextRow;
        }

        return queue;
    }

    public List<Integer> getRow2(int rowIndex) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        for (int i=0; i<rowIndex; i++) {
            int size = queue.size();
            int prev = queue.removeFirst();
            queue.add(1);
            for (int j=0; j<size-1; j++) {
                int removed = queue.removeFirst();
                queue.add(prev + removed);
                prev = removed;
            }
            queue.add(1); // ending 1
        }

        return queue;
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        System.out.println(runner.getRow(0));
        System.out.println(runner.getRow(1));
        System.out.println(runner.getRow(2));
        System.out.println(runner.getRow(3));
        System.out.println(runner.getRow(4));
        System.out.println(runner.getRow(5));
        System.out.println(runner.getRow(6));
    }
}
