package com.leetcode.problems.medium.sort_the_students_by_their_kth_score;

import java.util.PriorityQueue;

public class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(score.length, (a, b) -> Integer.compare(b.exam, a.exam));
        for (int i=0; i<score.length; i++) {
            pq.add(new Pair(i, score[i][k]));
        }
        int[][] sorted = new int[score.length][score[0].length];
        for (int i=0; i<score.length; i++) {
            Pair p = pq.remove();
            int row = p.student;
            System.arraycopy(score[row], 0, sorted[i], 0, score[row].length);
        }
        return sorted;
    }
    static class Pair {
        int student, exam;
        Pair(int s, int e) {
            student = s;
            exam = e;
        }
    }
}
