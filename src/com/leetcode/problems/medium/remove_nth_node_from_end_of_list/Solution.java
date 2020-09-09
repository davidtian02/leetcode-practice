package com.leetcode.problems.medium.remove_nth_node_from_end_of_list;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = findLength(head);
        if (len == 0) {
            return head;
        } else if (len == n) {
            return head.next;
        }

        int breaker = len - n - 1;
        ListNode temp = moveToBreaker(head, breaker);
        temp.next = temp.next.next;
        return head;
    }

    private int findLength(ListNode head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }

    private ListNode moveToBreaker(ListNode head, int breaker) {
        int i = 0;
        while (i < breaker) {
            head = head.next;
            i++;
        }
        return head;
    }

    // lol, doing it in one pass with... extra space?
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        Solution runner = new Solution();
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        ListNode result = runner.removeNthFromEnd(root, 2);
        printList(result);
    }

    private static void test2() {
        Solution runner = new Solution();
        ListNode root = new ListNode(1);
        ListNode result = runner.removeNthFromEnd(root, 1);
        printList(result);
    }

    private static void test3() {
        Solution runner = new Solution();
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        ListNode result = runner.removeNthFromEnd(root, 1);
        printList(result);
    }

    private static void printList(ListNode root) {
        List<String> tokens = new ArrayList<>();
        while (root != null) {
            tokens.add("" + root.val);
            root = root.next;
        }
        System.out.println(String.join(",", tokens));
    }
}