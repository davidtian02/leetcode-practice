package com.leetcode.problems.medium.swapping_nodes_in_a_linked_list;

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
    public ListNode swapNodes(ListNode head, int k) {
        int len = findLen(head);
        k = (k>len/2)? len-k+1 : k;
        ListNode kNode = jump(head, k-1);
        ListNode other = jump(kNode, len - (k-1) - k); // depends on even? write test case

        int temp = kNode.val;
        kNode.val = other.val;
        other.val = temp;

        return head;
    }

    private int findLen(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    private ListNode jump(ListNode head, int count) {
        while (count > 0) {
            head = head.next;
            count--;
        }

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}