package com.leetcode.problems.hard.reverse_nodes_in_k_group;

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
// https://leetcode.com/problems/reverse-nodes-in-k-group/
class Solution {
    // Runtime: TODO O(N)
    // space: O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        ListNode globalHead = head;
        ListNode[] headAndTails = reversion(head, k);
        globalHead = headAndTails[0];
        ListNode newHead, newTail;
        newHead = headAndTails[0];
        newTail = headAndTails[1];
        while (newTail != null) {
            headAndTails = reversion(newTail.next, k);
            ListNode nextHead = headAndTails[0];
            ListNode nextTail = headAndTails[1];

            // TODO feels something missing
            newTail.next = nextHead;
            newHead = nextHead;
            newTail = nextTail;
        }
        return globalHead;
    }

    private ListNode[] reversion(ListNode head, int len) {
        if (head == null) {
            return new ListNode[]{null, null};
        }

        int flips = 1;

        ListNode newTail = head;
        ListNode localHead, tempNext=null;

        ListNode lookahead = head.next;
        // TODO see if can match len, else return
        if (notLongEnough(head, len)) {
            return new ListNode[] {head, null};
        }

        // flip, plus 1
        while (flips < len) {
            tempNext = lookahead.next;
            lookahead.next = head;
            head = lookahead;
            lookahead = tempNext;
            flips++;
        }

        newTail.next = tempNext;
        return new ListNode[]{head, newTail};
    }

    private boolean notLongEnough(ListNode head, int len) {
        int i=0;
        while (i<len) {
            if (head == null) {
                break;
            }
            head = head.next;
            i++;
        }
        return i<len;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
