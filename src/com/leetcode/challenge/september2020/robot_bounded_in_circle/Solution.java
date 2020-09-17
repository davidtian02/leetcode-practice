package com.leetcode.challenge.september2020.robot_bounded_in_circle;

// https://leetcode.com/explore/featured/card/september-leetcoding-challenge/556/week-3-september-15th-september-21st/3463/
class Solution {
    public boolean isRobotBounded(String instructions) {
        Node current = new Node(0, 0, Direction.up);
        for (int i=0; i<4; i++) {
            if (followOnceHitsOrigin(instructions, current)) {
                return true;
            }
        }
        return followOnceHitsOrigin(instructions, current);
    }

    private boolean followOnceHitsOrigin(String instructions, Node current) {
        for (int i=0, len=instructions.length(); i<len; i++) {
            char c = instructions.charAt(i);
            switch (c) {
                case 'G': goStraight(current); break;
                case 'L': spinLeft(current); break;
                case 'R': spinRight(current); break;
            }
        }
        return isOrigin(current);
    }

    private void goStraight(Node current) {
        switch (current.dir) {
            case up: current.y++; break;
            case right: current.x++; break;
            case down: current.y--; break;
            case left: current.x--; break;
        }
    }

    private void spinLeft(Node current) {
        switch (current.dir) {
            case up: current.dir = Direction.left; break;
            case right: current.dir = Direction.up; break;
            case down: current.dir = Direction.right; break;
            case left: current.dir = Direction.down; break;
        }
    }

    private void spinRight(Node current) {
        switch (current.dir) {
            case up: current.dir = Direction.right; break;
            case right: current.dir = Direction.down; break;
            case down: current.dir = Direction.left; break;
            case left: current.dir = Direction.up; break;
        }
    }

    private boolean isOrigin(Node current) {
        return current.x == 0 && current.y == 0; //&& current.dir == Direction.up;
    }

    enum Direction {
        up, left, down, right
    }
    static class Node {
        int x, y;
        Direction dir;
        Node(int a, int b, Direction d) {
            x = a;
            y = b;
            dir = d;
        }
    }
}
