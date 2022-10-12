package com.leetcode.problems.medium.course_schedule;

import java.util.*;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> unlocks = new HashMap<>();
        Map<Integer, Set<Integer>> dependencies = new HashMap<>();
        Set<Integer> taking = new HashSet<>();
        Set<Integer> taken = new HashSet<>();

        for (int i=0; i<numCourses; i++) {
            taking.add(i);
        }

        for (int[] prereq : prerequisites) {
            int c = prereq[0];
            int p = prereq[1];
            if (!unlocks.containsKey(p)) {
                unlocks.put(p, new HashSet<>());
            }
            unlocks.get(p).add(c);

            if (!dependencies.containsKey(c)) {
                dependencies.put(c, new HashSet<>());
            }
            dependencies.get(c).add(p);

            taking.remove(c);
        }

        while(!taking.isEmpty()) {
            Set<Integer> nextUp = new HashSet<>();
            for (Integer course : taking) {
                if (unlocks.containsKey(course)) {
                    for (Integer u : unlocks.get(course)) {
                        dependencies.get(u).remove(course);
                        if (dependencies.get(u).isEmpty()) {
                            nextUp.add(u);
                        }
                    }
                }
                taken.add(course);
            }

            taking = nextUp;
        }

        return taken.size() == numCourses;
    }
}
