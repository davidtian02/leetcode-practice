package com.leetcode.problems.medium.rotate_list;

// https://leetcode.com/problems/rotate-list/
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            len++;
        }

        int steps = k%len;
        if (steps == 0 || len == 1) {
            return head;
        }

        int newTail = len-steps-1;
        int i=0;
        temp = head;
        while(i<newTail) {
            temp = temp.next;
            i++;
        }
        ListNode newTailNode = temp;
        ListNode newHeadNode = temp.next;
        newTailNode.next = null;
        temp = newHeadNode;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;

        return newHeadNode;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
