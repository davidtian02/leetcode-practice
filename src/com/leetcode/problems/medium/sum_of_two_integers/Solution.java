package com.leetcode.problems.medium.sum_of_two_integers;

import java.util.Stack;

class Solution {
    public int getSum(int a, int b) {
        if (a < 0 && b < 0) {
            return -1 * add(Math.abs(a), Math.abs(b));
        } else if (a < 0 || b < 0) {
            if (a < 0) { // neg always last
                int temp = a;
                a = b;
                b = temp;
            }

            if (a > Math.abs(b)) { // pos is bigger, just subtract
                return minus(a, Math.abs(b));
            } else { // if neg is clearly bigger
                return -1 * minus(Math.abs(b), a);
            }
        } else {
            return add(a, b);
        }
    }

    private int add(int a, int b) {
        int carry = 0;
        Stack<Integer> stack = new Stack<>();
        int x = 0, y = 0;
        while (a != 0 || b != 0) {
            x = a & 0x01;
            y = b & 0x01;
            int bit = 0;
            if (x == 1 && y == 1) {
                bit = carry;
                carry = 1;
            } else if (x == 1 || y == 1) {
                bit = carry == 1 ? 0 : 1;
            } else { // both 0
                bit = carry;
                carry = 0;
            }
            stack.push(bit);
            a >>= 1;
            b >>= 1;
        }
        if (carry == 1) {
            stack.push(carry);
        }

        return convert(stack);
    }

    private int minus(int a, int b) {
        boolean borrow = false;
        Stack<Integer> stack = new Stack<>();
        int x = 0, y = 0;
        while (a != 0 || b != 0) {
            x = a & 0x01;
            y = b & 0x01;
            int bit = 0;
            if (x == 1 && y == 1) {
                bit = borrow ? 1 : 0;
            } else if (x == 1 || y == 1) {
                bit = borrow ? 0 : 1;
                borrow = y == 1;
            } else { // both 0
                bit = borrow ? 1 : 0;
            }
            stack.push(bit);
            a >>= 1;
            b >>= 1;
        }
        return convert(stack);
    }

    private int convert(Stack<Integer> stack) {
        int result = 0;
        while (!stack.isEmpty()) {
            int s = stack.pop();
            result |= s;
            result <<= 1;
        }
        return result >> 1;
    }
}
