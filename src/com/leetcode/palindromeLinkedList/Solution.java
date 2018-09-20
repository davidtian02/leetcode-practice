package com.leetcode.palindromeLinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 12:03
class Solution {
    // https://leetcode.com/submissions/detail/177275089/ this is done in constant space and linear time.
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        int len = countLength(head);
        int mid = len >> 1;

        ListNode halfway = traverseTo(head, mid);
        ListNode head2 = halfway.next;
        if ((len & 0x01) == 1) {
            head2 = head2.next;
            halfway = halfway.next;
        }
        halfway.next = null;

        ListNode l2 = reverse(head2);
        boolean result = checkPalindrome(head, l2);
        head2 = reverse(l2);
        halfway.next = head2;
        return result;
    }

    private ListNode reverse(ListNode root) {
        ListNode temp = root;
        if (temp.next == null) {
            return root;
        }
        ListNode temp1 = temp.next;
        if (temp1.next == null) {
            temp1.next = temp;
            temp.next = null;
            return temp1;
        }

        ListNode temp2 = temp1.next;
        if (temp2.next == null) {
            temp.next = null;
            temp1.next = temp;
            temp2.next = temp1;
            return temp2;
        }

        temp.next = null;
        while(temp2.next != null) {
            temp1.next = temp;
            temp = temp1;
            temp1 = temp2;
            temp2 = temp2.next;
        }

        temp2.next = temp1;
        temp1.next = temp;

        return temp2;
    }

    private boolean checkPalindrome(ListNode l1, ListNode l2) {
        ListNode a = l1;
        ListNode b = l2;
        while (a != null && b != null) {
            if (a.val != b.val) {
                return false;
            }

            a = a.next;
            b = b.next;
        }

        return true;
    }

    private int countLength(ListNode root) {
        int len = 0;
        ListNode temp = root;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    private ListNode traverseTo(ListNode root, int count) {
        int i = 1;
        ListNode temp = root;
        while (i<count) {
            temp = temp.next;
            i++;
        }
        return temp;
    }
}