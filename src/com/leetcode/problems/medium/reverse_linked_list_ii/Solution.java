package com.leetcode.problems.medium.reverse_linked_list_ii;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//https://leetcode.com/problems/reverse-linked-list-ii/description/

class Solution {

    public static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m < 1 || m >= n || head == null) {
            return head;
        }

        ListNode left, right, temp, newTail;
        int i = 1;
        temp = head;

        if (m == 1) {
            left = null;
            temp = null;
        } else {
            while(i < m - 1) {
                temp = temp.next;
                i++;
            }
            left = temp;
            temp = temp.next;

            if (temp == null) {
                // no starting point to reverse, m is len of list
                return head;
            }
        }

        // by now left is the node right before we need to start reversing. OR left could be null, in case m == 1
        // and temp is the node right after (where we start reversing from)
        if (left == null) {
            temp = head;
            newTail = null;
        } else {
            newTail = left.next;
            left.next = null; // break linkage.
        }

        i = m;
        ListNode prev = temp;
        temp = temp.next;
        if (temp == null) {
            // nothing to reverse
            return head;
        }

        ListNode tempNext = temp.next; // moved temp already.
        while (i < n - 1) {
            temp.next = prev;
            prev = temp;
            temp = tempNext;
            tempNext = tempNext.next;
            i++;
        }
        // wrap up
        temp.next = prev;

        if (tempNext == null) {
            // we reached end
            if (left == null) {
                head.next = null; // wait
                return temp; // temp is now the new head
            } else {
                newTail.next = null;
                left.next = temp;
                return head;
            }
        } else {
            if (left == null) {
                head.next = tempNext;
                return temp;
            } else {
                left.next = temp;
                newTail.next = tempNext;
            }
        }

        return head;
    }
}
