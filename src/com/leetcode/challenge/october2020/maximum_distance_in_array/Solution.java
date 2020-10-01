package com.leetcode.challenge.october2020.maximum_distance_in_array;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3479/
class Solution {

    public int maxDistance(List<List<Integer>> arrays) {
        if (arrays.size() < 2) {
            return 0;
        }
        // find max1, max2, and for mins
        PriorityQueue<Pair> min = new PriorityQueue<>(3, Comparator.comparingInt(a -> a.val));
        PriorityQueue<Pair> max = new PriorityQueue<>(3, (a, b) -> Integer.compare(b.val, a.val)); // only need 2. but can scale

        for (int i=0; i<arrays.size(); i++) {
            List<Integer> list = arrays.get(i);
            if (!list.isEmpty()) {
                int tempMin = list.get(0);
                int tempMax = list.get(list.size()-1); // it's okay if it's the same element.
                Pair minP = new Pair(tempMin, i);
                Pair maxP = new Pair(tempMax, i);

                min.add(minP);
                max.add(maxP);

                if (min.size() > 2) evict(min);
                if (max.size() > 2) evict(max);
            }
        }

        Pair smallestMin = min.poll();
        Pair secondSmallestMin = min.poll();
        Pair biggestMax = max.poll();
        Pair secondBiggestMax = max.poll();
        // provided it's not the same array. else, put this in the else statement
        if (smallestMin.arrNumber != biggestMax.arrNumber) {
            return Math.abs(biggestMax.val - smallestMin.val);
        } else {
            int d1 = Math.abs(biggestMax.val - secondSmallestMin.val);
            int d2 = Math.abs(secondBiggestMax.val - smallestMin.val);
            return Math.max(d1, d2);
        }
    }

    private void evict(PriorityQueue<Pair> pq) {
        Pair a = pq.poll();
        Pair b = pq.poll();
        pq.poll(); // evict
        pq.add(a);
        pq.add(b);
    }

    static class Pair {
        int val;
        int arrNumber;
        Pair(int v, int i) {
            val = v;
            arrNumber = i;
        }
    }
}
