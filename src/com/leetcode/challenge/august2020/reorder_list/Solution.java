package com.leetcode.challenge.august2020.reorder_list;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/551/week-3-august-15th-august-21st/3430/
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int len = stack.size();
        int mid = len>>1;

        ListNode start, end, next;
        start = head;
        end = stack.pop();
        for (int i=0; i<mid; i++) {
            next = start.next;
            start.next = end;
            end.next = next;

            start = next;
            end = stack.pop();
        }

        if ((len & 0x01) == 0) {
            end = end.next;
        }
        end.next = null;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String... args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;

        Solution runner = new Solution();
        runner.reorderList(a);

        printList(a);
    }

    private static void printList(ListNode a) {
        StringBuilder sb = new StringBuilder();
        ListNode temp = a;
        while (temp != null) {
            sb.append(temp.val).append(", ");
            temp = temp.next;
        }
        System.out.println(sb.toString());
    }
}