package com.leetcode.challenge.october2020.number_of_recent_calls;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3480/
class RecentCounter {
    private static final int BUFFER_BOUNDARY = 3000;
    Queue<Integer> pings; // actually, because t <= 10000, we could actually just use a simple array. but i'm assuming massive amounts of calls to "ping". so optimizing for that
    public RecentCounter() {
        pings = new LinkedList<>();
    }

    public int ping(int t) {
        while (!pings.isEmpty() && pings.peek() < (t-BUFFER_BOUNDARY)) pings.poll();
        pings.offer(t);
        return pings.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
