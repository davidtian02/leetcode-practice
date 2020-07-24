package com.leetcode.problems.medium.angle_between_hands_of_a_clock;

// https://leetcode.com/problems/angle-between-hands-of-a-clock/
public class Solution {
    // use relative to midnight
    public double angleClock(int hour, int minutes) {
        double h = calcDegreesHour(hour, minutes);
        double m = calcDegreesMinutes(minutes);
        double diff = Math.abs(h - m);
        if (diff > 180) {
            diff = 360 - diff;
        }
        return diff;
    }

    private double calcDegreesHour(int hour, int min) {
        return (hour * 30) + (min * 0.5);
    }

    private double calcDegreesMinutes(int min) {
        return min * 6;
    }
    // each minute is 6 degrees, cuz 360/60 = 6, could be 12:00
    // each hour is 360/12 = 30 degrees; but each minute then adds 30/60 = 0.5 degrees
}
