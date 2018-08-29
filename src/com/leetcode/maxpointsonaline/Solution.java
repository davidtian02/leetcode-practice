package com.leetcode.maxpointsonaline;

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

// submission here: https://leetcode.com/submissions/detail/172348795/
class Solution {
    public int maxPoints(Point[] points) {
        if (points == null || points.length < 2) {
            return points.length; // for invalid
        }

        int currentMax = 2;
        for (int i=0; i<points.length; i++) {
            for (int j=i+1; j<points.length; j++) {
                List<Point> otherPoints = new LinkedList<>();
                for (int k = 0; k<points.length; k++) {
                    if (k != i && k != j) {
                        otherPoints.add(points[k]);
                    }
                }
                Line line = new Line(points[i], points[j]);
                int numberOfPointsThatFitInThisLine = findPointsInLine(otherPoints, line);
                if (numberOfPointsThatFitInThisLine > currentMax) {
                    currentMax = numberOfPointsThatFitInThisLine;
                }
            }
        }

        return currentMax;
    }

    private int findPointsInLine(List<Point> points, Line line) {
        int numberOfPoints = 2;
        for (Point p : points) {
            if (liesInSameLine(line, p)) {
                numberOfPoints++;
            }
        }

        return numberOfPoints;
    }

    private boolean liesInSameLine(Line line, Point target) {
        // watch for vertical lines
        // y = mx + b
        // m = (y2 - y1) / (x2 - x1)
        // m2 == dz / dx
        double delta = 0.000000000001f;

        if (line.p2.x == line.p1.x) { // vertical line
            return target.x == line.p1.x;
        } else {
            if (target.x == line.p2.x) {
                return target.y == line.p2.y;
            }
        }

        double slope1 = (line.p2.y - line.p1.y) / (double)(line.p2.x - line.p1.x);
        double slope2 = (target.y - line.p2.y) / (double)(target.x - line.p2.x);

        return Math.abs(slope1 - slope2) < delta;

    }

    static class Line {
        Point p1;
        Point p2;
        Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}