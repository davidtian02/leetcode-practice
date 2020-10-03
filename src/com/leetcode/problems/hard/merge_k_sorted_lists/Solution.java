package com.leetcode.problems.hard.merge_k_sorted_lists;

import java.util.PriorityQueue;

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
    // []
    // negatives? long?
    // how many elements?
    // duplicate elements?
    // let's use PQ of pairs
    // Runtime Complexity: O(N LOG M) where M is number of lists, and N is number of elements. since we process all elements of the lists. but we use logM time for each, because we are comparing with the front of every other list
    // Spacetime Complexity: O(N) where N is number of lists
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length+1, (a, b) -> Integer.compare(a.val, b.val));
        init(lists, pq);
        return traverse(pq);
    }

    private ListNode traverse(PriorityQueue<ListNode> pq) {
        ListNode head = null;
        ListNode tail = null;
        while (!pq.isEmpty()) {
            ListNode current = pq.remove();
            if (head == null) {
                head = current;
                tail = head;
            } else {
                tail.next = current;
                tail = current;
            }

            current = current.next;
            if (current != null) {
                pq.add(current);
            }
        }
        return head;
    }

    private void init(ListNode[] heads, PriorityQueue<ListNode> pq) {
        for (ListNode h : heads) {
            if (h != null) {
                pq.add(h);
            }
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
