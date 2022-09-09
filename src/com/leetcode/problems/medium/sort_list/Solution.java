package com.leetcode.problems.medium.sort_list;

import java.util.ArrayList;
import java.util.Collections;

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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode right = splitRight(head);
        left = sortList(left);
        right = sortList(right);
        head = merge(left, right);
        return head;
    }

    private ListNode splitRight(ListNode head) {
        int stepsRemaining = (findLen(head) >> 1);
        ListNode temp = head;
        while (stepsRemaining>1) {
            temp = temp.next;
            stepsRemaining--;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode merged, head;
        if (left.val < right.val) {
            merged = left;
            left = left.next;
        } else {
            merged = right;
            right = right.next;
        }

        head = merged;

        while (left != null || right != null) {
            if (left == null) {
                merged.next = right;
                merged = merged.next;
                right = right.next;
            } else if (right == null) {
                merged.next = left;
                merged = merged.next;
                left = left.next;
            } else {
                if (left.val < right.val) {
                    merged.next = left;
                    merged = merged.next;
                    left = left.next;
                } else {
                    merged.next = right;
                    merged = merged.next;
                    right = right.next;
                }
            }
        }

        return head;
    }

    private int findLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode sortList2(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        Collections.sort(list);
        temp = head;
        for (int i=0; i<list.size(); i++) {
            temp.val = list.get(i);
            temp = temp.next;
        }
        return head;
    }
}