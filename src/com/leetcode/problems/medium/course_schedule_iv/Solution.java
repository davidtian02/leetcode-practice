package com.leetcode.problems.medium.course_schedule_iv;

import java.util.*;

class Solution {
    // 4:05 - 4:53
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> inverseMatrix = invertMatrix(numCourses, prerequisites);
        Set<Integer> prereqs[] = new HashSet[numCourses];

        for (int i=0; i<numCourses; i++) {
            bfs(inverseMatrix, i, prereqs);
        }

        List<Boolean> result = new LinkedList<>();
        for (int[] q : queries) {
            result.add(prereqs[q[1]].contains(q[0]));
        }
        return result;
    }

    private Map<Integer, Set<Integer>> invertMatrix(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i=0; i<numCourses; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] pair : prerequisites) {
            map.get(pair[1]).add(pair[0]);
        }
        return map;
    }

    private void bfs(Map<Integer, Set<Integer>> inverseMatrix, int course, Set<Integer> prereqs[]) {
        prereqs[course] = new HashSet<>();

        Queue<Integer> previous = new LinkedList<>();
        previous.add(course);

        Set<Integer> visited = new HashSet<>();
        while (!previous.isEmpty()) {
            Queue<Integer> next = new LinkedList<>();
            Iterator<Integer> iter = previous.iterator();
            while(iter.hasNext()) {
                int n = iter.next();
                Set<Integer> prs = inverseMatrix.get(n);
                for (int s : prs) {
                    if (!visited.contains(s)) {
                        next.add(s);
                        prereqs[course].add(s);
                        visited.add(s);
                    }
                }
            }
            previous = next;
        }
    }
}