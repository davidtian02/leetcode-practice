package com.leetcode.problems.medium.swap_nodes_in_pairs;

import java.util.ArrayList;

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
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode h1, h2, t1, t2;
        h1 = head;
        h2 = head.next;
        t1 = h1;
        t2 = h2;

        while (t2.next != null && t2.next.next != null) {
            t1.next = t2.next;
            t2.next = t2.next.next;
            t1 = t1.next;
            t2 = t2.next;
        }

        if (t2.next == null) {
            t1.next = null;
        } else {
            t1.next = t2.next;
            t2.next = null;
        }

        // merge. with h2 first, and h2 size is always either same or smaller
        ListNode zip = h2;
        ListNode th1 = h1;
        ListNode th2 = h2;
        ListNode tail = h1;
        while (h2 != null) {
            h2 = h2.next;
            h1 = h1.next;
            th2.next = th1;
            th1.next = h2;
            tail = th1;
            th2 = h2;
            th1 = h1;
        }
        if (h1 != null) {
            tail.next = h1;
        }

        return zip;
    }

    private String strList(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while(head != null) {
            sb.append(head.val).append(',');
            head = head.next;
        }
        return sb.append("]").toString();
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null) {
            return null;
        }

        ArrayList<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            // ListNode previous = head;
            head = head.next;
            // previous.next = null;
        }
        for (int i=0,j=1; j<list.size(); i+=2,j+=2) {
            ListNode temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        ListNode h = list.get(0);
        for (int i=0; i<list.size()-1; i++) {
            h.next = list.get(i+1);
            h = h.next;
        }
        h.next = null;

        return list.get(0);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}