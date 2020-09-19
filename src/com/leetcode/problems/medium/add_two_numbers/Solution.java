package com.leetcode.problems.medium.add_two_numbers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    // https://leetcode.com/submissions/detail/171319044/ <--- here is the submission. my assumption is that this integer could be a over a million digits long. took about 80 min long for the entire thing.

    private static final int DECIMAL = 10;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list1 = convert(l1); // 2, 4, 3
        List<Integer> list2 = convert(l2); // 5, 6, 4

        equalizeSizes(list1, list2);

        Iterator iter1 = list1.iterator();
        Iterator iter2 = list2.iterator();
        int carry = 0;

        List<Integer> result = new LinkedList<>();

        while (iter1.hasNext()) { // we have equalized the lists now
            int value = (int) iter1.next() + (int) iter2.next() + carry;

            carry = value / DECIMAL;
            value = value % DECIMAL;

            result.add(value);
        }

        if (carry > 0) {
            result.add(carry);
        }

        return convertList(result);
    }

    private void equalizeSizes(List<Integer> list1, List<Integer> list2) {
        while (list1.size() > list2.size()) {
            list2.add(0);
        }
        while (list2.size() > list1.size()) {
            list1.add(0);
        }
    }

    private List<Integer> convert(ListNode root) {
        ListNode temp = root;
        List<Integer> result = new LinkedList<>();

        while (temp != null) {
            result.add(temp.val);
            temp = temp.next;
        }

        return result;
    }

    private ListNode convertList(List<Integer> list) {
        ListNode temp = null;
        ListNode root = null;
        for (Integer i : list) {
            if (temp == null) {
                temp = new ListNode(i);
                root = temp;
            } else {
                temp.next = new ListNode(i);
                temp = temp.next;
            }
        }

        return root;
    }
}