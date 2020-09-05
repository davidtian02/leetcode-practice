package com.leetcode.problems.easy.merge_two_sorted_lists;

// https://leetcode.com/problems/merge-two-sorted-lists/
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result, temp;
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else { // both have initial value
            ListNode temp1, temp2;
            if (l1.val < l2.val) {
                result = l1;
                temp1 = l1.next;
                temp2 = l2;
            } else {
                result = l2;
                temp1 = l1;
                temp2 = l2.next;
            }

            temp = result;
            while(temp1 != null || temp2 != null) {
                if (temp1 == null) { // pick t2
                    temp.next = temp2;
                    temp = temp2;
                    temp2 = temp2.next;
                } else if (temp2 == null) { // pick t1
                    temp.next = temp1;
                    temp = temp1;
                    temp1 = temp1.next;
                } else {
                    if (temp1.val < temp2.val) { // pick t1
                        temp.next = temp1;
                        temp = temp1;
                        temp1 = temp1.next;
                    } else { // pick t2
                        temp.next = temp2;
                        temp = temp2;
                        temp2 = temp2.next;
                    }
                }
            }
        }
        return result;
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
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        ListNode result = runner.mergeTwoLists(l1, l2);
        System.out.println(result);
    }
}