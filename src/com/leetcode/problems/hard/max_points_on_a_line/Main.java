package com.leetcode.problems.hard.max_points_on_a_line;

public class Main {
    public static void main(String... args) {
        Solution solution = new Solution();
//        int points = solution.maxPoints(new Point[]{
//                // [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//                new Point(1, 1),
//                new Point(3, 2),
//                new Point(5, 3),
//                new Point(4, 1),
//                new Point(2, 3),
//                new Point(1, 4)
//        });

        int points = solution.maxPoints(new Point[]{
                // [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2)
        });

        System.out.println(points);
    }
}
