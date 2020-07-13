package com.leetcode.deleteNodeInALinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// https://leetcode.com/problems/delete-node-in-a-linked-list/
class Solution {

    public void deleteNode(ListNode node) {
        ListNode temp = node.next; // since input node can't be tail
        while (temp.next != null) {
            node.val = temp.val;
            node = temp;
            temp = temp.next; // scootch over
        }
        node.val = temp.val;
        node.next = null;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}