package com.leetcode.problems.medium.odd_even_linked_list;

// https://leetcode.com/problems/odd-even-linked-list/
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        return shuffleList(head, head.next);
    }

    private ListNode shuffleList(ListNode oddHead, ListNode evenHead) {
        ListNode temp1 = oddHead, temp2 = evenHead;
        while (temp2.next != null && temp2.next.next != null) { // then we are done
            ListNode t1 = temp2.next, t2 = temp2.next.next;
            temp1.next = t1;
            temp2.next = t2;
            temp1 = t1;
            temp2 = t2;
        }

        if (temp2.next != null) {
            // one more straggler
            temp1.next = temp2.next;
            temp2.next = null;
            temp1 = temp1.next;
        } // else, it means we reached end of list, nothing else to do

        temp1.next = evenHead;
        return oddHead;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String... args) {
        Solution runner = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode newHead = runner.oddEvenList(head);
        System.out.println(newHead);
    }
}