package com.leetcode.problems.easy.reverse_linked_list;

// https://leetcode.com/problems/reverse-linked-list/
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = reverseList(head.next);
        head.next.next = head; // this can only be done because the old head still pointed to the one that will become the one right above it
        head.next = null;
        return next;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newHead = recurseReverse(head, head.next);
        head.next = null;
        return newHead;
    }

    private ListNode recurseReverse(ListNode prev, ListNode next) {
        if (next == null) {
            return prev;
        }
        ListNode newHead = recurseReverse(next, next.next);
        next.next = prev;
        return newHead;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        ListNode original = new ListNode(5);
        original.next = new ListNode(4);
        original.next.next = new ListNode(3);
        original.next.next.next = new ListNode(2);
        original.next.next.next.next = new ListNode(1);
        ListNode reversed = runner.reverseList(original);
        System.out.println(reversed);
    }
}