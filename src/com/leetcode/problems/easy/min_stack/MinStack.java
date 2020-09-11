package com.leetcode.problems.easy.min_stack;

// https://leetcode.com/problems/min-stack/
class MinStack {

    Node head;
    /** initialize your data structure here. */
    public MinStack() {
        head = null;
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            int min = Math.min(x, head.min);
            Node t = new Node(x, min);
            t.next = head;
            head = t;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    static class Node {
        int val;
        Node next;
        int min;
        Node(int v, int m) {
            val = v;
            min = m;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
