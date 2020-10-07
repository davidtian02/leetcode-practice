package com.leetcode.challenge.october2020.rotate_list;

import java.util.ArrayDeque;
import java.util.Deque;

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
// https://leetcode.com/explore/featured/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3486/
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        Deque<ListNode> deq = new ArrayDeque<>();
        if (k == 0 || head == null || head.next == null) return head;
        while (head != null) {
            deq.addLast(head);
            head = head.next;
        }
        k %= deq.size();
        int i=0;
        while (i<k) {
            ListNode prevLast = deq.removeLast();
            prevLast.next = deq.peekFirst();
            deq.addFirst(prevLast);
            ListNode currentLast = deq.peekLast();
            currentLast.next = null;
            i++;
        }

        return deq.peekFirst();
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
