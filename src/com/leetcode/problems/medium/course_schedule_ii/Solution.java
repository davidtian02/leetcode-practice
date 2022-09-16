package com.leetcode.problems.medium.course_schedule_ii;

import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> dependenciesMap = new HashMap<>();
        Map<Integer, Set<Integer>> dependedUponMap = new HashMap<>();
        Set<Integer> starting = populateDependenciesReturnsStarting(numCourses, dependenciesMap, dependedUponMap, prerequisites);
        List<Integer> courses = takeClasses(starting, dependenciesMap, dependedUponMap);
        if (courses.size() < numCourses) {
            return new int[0];
        }
        return courses.stream().mapToInt(i -> i).toArray();
    }

    private Set<Integer> populateDependenciesReturnsStarting(int numCourses, Map<Integer, Set<Integer>> dependenciesMap, Map<Integer, Set<Integer>> dependedUponMap, int[][] prereqs) {
        Set<Integer> startingCourses = new HashSet<>();
        for (int i=0; i<numCourses; i++) {
            dependenciesMap.put(i, new HashSet<>());
            dependedUponMap.put(i, new HashSet<>());
            startingCourses.add(i);
        }

        for (int[] pr : prereqs) {
            dependenciesMap.get(pr[0]).add(pr[1]);
            startingCourses.remove(pr[0]);
            dependedUponMap.get(pr[1]).add(pr[0]);
        }
        return startingCourses;
    }

    private List<Integer> takeClasses(Set<Integer> courses, Map<Integer, Set<Integer>> dependenciesMap, Map<Integer, Set<Integer>> dependedUponMap) {
        List<Integer> coursesTaken = new LinkedList<>();
        while(!courses.isEmpty()) {
            Integer current = courses.iterator().next();
            courses.remove(current);
            coursesTaken.add(current);

            Set<Integer> nextUpCandidates = dependedUponMap.get(current);
            dependedUponMap.remove(current);

            for (Integer candidate : nextUpCandidates) {
                Set<Integer> dependencies = dependenciesMap.get(candidate);
                dependencies.remove(current);

                if (dependencies.isEmpty()) {
                    courses.add(candidate);
                }
            }
        }
        return coursesTaken;
    }
}

