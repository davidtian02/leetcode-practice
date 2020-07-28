package com.leetcode.challenge.july2020.task_scheduler;

import java.util.*;

// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/547/week-4-july-22nd-july-28th/3404/
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int count = 0;
        // get frequency list
        List<Map.Entry<Character, Integer>> frequency = constructFrequencyList(tasks);
        // make queue cache
        HistoryList history = new HistoryList(n);
        // keep going down the line
        while (frequency.get(0).getValue() > 0) { // still tasks to do
            doNextTask(frequency, history);
            count++;
        }

        return count;
    }

    private void doNextTask(List<Map.Entry<Character, Integer>> frequency, HistoryList history) {
        int taskIndex = -1;
        for (int i=0; i<frequency.size(); i++) {
            if (!history.contains(frequency.get(i).getKey())) {
                taskIndex = i;
                break;
            }
        }

        if (taskIndex >= 0) {
            Map.Entry<Character, Integer> entry = frequency.get(taskIndex);
            history.add(entry.getKey());
            entry.setValue(entry.getValue()-1);
            for (int i=taskIndex; i<frequency.size()-1; i++) {
                if (frequency.get(i).getValue() < frequency.get(i+1).getValue()) { // bubble down lower frequency to end
                    Map.Entry<Character, Integer> temp = frequency.get(i);
                    frequency.set(i, frequency.get(i+1));
                    frequency.set(i+1, temp);
                }
            }
        } else {
            history.add('_'); // idle, cuz everything existed
        }
    }

    private List<Map.Entry<Character, Integer>> constructFrequencyList(char[] tasks) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : tasks) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c)+1);
            } else {
                freq.put(c, 1);
            }
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((me, other) -> {
            if (me.getValue() < other.getValue()) {
                return 1;
            } else if (me.getValue() > other.getValue()) {
                return -1;
            } else {
                return 0;
            }
        });
        return list;
    }

    static class HistoryList {
        private final int capacity;
        private final Queue<Character> records;
        private final Map<Character, Integer> counter;
        HistoryList(int capacity) {
            this.capacity = capacity;
            records = new LinkedList<>();
            counter = new HashMap<>();
        }

        boolean contains(Character key) {
            return counter.containsKey(key) && counter.get(key)>0;
        }

        void add(Character key) {
            records.add(key);
            if (counter.containsKey(key)) {
                counter.put(key, counter.get(key) + 1);
            } else {
                counter.put(key, 1);
            }

            if (records.size() > capacity) {
                Character removed = records.remove();
                counter.put(removed, counter.get(removed)-1);
            }
        }
    }

    public static void main(String...args) {
        Solution runner = new Solution();
//        System.out.println(runner.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
//        System.out.println(runner.leastInterval(new char[]{'A','A','A','B','B','B'}, 0));
//        System.out.println(runner.leastInterval(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G',}, 2));

        long start = System.currentTimeMillis();
        int result = runner.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 50);
        long end = System.currentTimeMillis();
        System.out.println(result + "  " + (end-start) + "ms");
    }
}
