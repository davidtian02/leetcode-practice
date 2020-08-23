package com.leetcode.challenge.august2020.random_point_in_non_overlapping_rectangles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3433/
class Solution {
    List<Rectangle> areasList;
    int totalArea = 0;
    Random rng;

    public Solution(int[][] rects) {
        rng = new Random();
        areasList = new ArrayList<>(rects.length);
        int index = 0;
        for (int[] r : rects) {
            Rectangle rect = new Rectangle(r[0], r[1], r[2], r[3], rng);
            int area = rect.area();
            rect.setRange(index, index+area);
            areasList.add(rect);
            index += area;
            totalArea += area;
        }
    }

    public int[] pick() {
        int index = rng.nextInt(totalArea);
        Rectangle selected = findRectangle(index, 0, areasList.size()-1);
        return selected.pickPoint();
    }

    private Rectangle findRectangle(int target, int start, int end) {
        // binary search it
        if (start == end) {
            return areasList.get(start);
        }
        int mid = ((end - start)>>1) + start;
        Rectangle rect = areasList.get(mid);
        if (rect.containsIndex(target)) {
            return rect;
        } else if (rect.isAfter(target)) {
            return findRectangle(target, start, mid-1);
        } else {
            return findRectangle(target, mid+1, end);
        }
    }

    static class Rectangle {
        int x1, y1, x2, y2;
        int index1, index2;
        Random rng;
        Rectangle(int a, int b, int c, int d, Random rng) {
            x1 = a;
            y1 = b;
            x2 = c;
            y2 = d;
            this.rng = rng;
        }
        int[] pickPoint() {
            int x = rng.nextInt((x2-x1+1)) + x1;
            int y = rng.nextInt((y2-y1+1)) + y1;
            return new int[]{x, y};
        }
        int area() {
            return (x2-x1+1) * (y2-y1+1);
        }

        void setRange(int i1, int i2) {
            index1 = i1;
            index2 = i2;
        }

        boolean containsIndex(int index) {
            return index1 <= index && index <= index2;
        }

        boolean isAfter(int index) {
            return index1 > index;
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */