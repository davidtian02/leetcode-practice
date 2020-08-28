package com.leetcode.challenge.august2020.implement_rand10_using_rand7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3439/
class Solution {
    private Random random = new Random();
    private int rand7() {
        return random.nextInt(7) + 1;
    }

    public int rand10() {
        return tieBreaker(IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()));
    }

    private int tieBreaker(List<Integer> remainingIndices) {
        Map<Integer, Integer> competition = new HashMap<>();
        int max = 0;
        for (int i : remainingIndices) {
            int r7 = rand7();
            competition.put(i, r7);
            if (r7 > max) {
                max = r7;
            }
        }

        List<Integer> nextCompetitors = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : competition.entrySet()) {
            if (entry.getValue() == max) {
                nextCompetitors.add(entry.getKey());
            }
        }

        if (nextCompetitors.size() == 1) {
            return nextCompetitors.get(0); // winner
        }

        return tieBreaker(nextCompetitors);
    }
}
