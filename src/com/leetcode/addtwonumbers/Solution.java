package com.leetcode.addtwonumbers;

import java.util.Collections;
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

    private static final int DECIMAL = 10;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> correctOrderList1 = convert(l1); // 2, 4, 3
        List<Integer> correctOrderList2 = convert(l2); // 5, 6, 4

        if (correctOrderList2.size() > correctOrderList1.size()) {
            List<Integer> temp = correctOrderList1;
            correctOrderList1 = correctOrderList2;
            correctOrderList2 = temp;
        }

        Iterator iter1 = correctOrderList1.iterator();
        Iterator iter2 = correctOrderList2.iterator();
        int value1 = 0;
        int value2 = 0;
        int carry = 0;

        List<Integer> result = new LinkedList<>();

        while (iter1.hasNext() || iter2.hasNext()) { // we assume list 1 is always the longer one
            if (iter1.hasNext()) {
                value1 = (int) iter1.next();
            } else {
                result.add((Integer) iter2.next() + carry);
                continue;
            }

            if (iter2.hasNext()) {
                value2 = (int) iter2.next();
            } else {
                result.add(value1 + carry);
                continue;
            }

            int value = value1 + value2 + carry;

            carry = value / DECIMAL;
            value = value % DECIMAL;

            result.add(value);
        }

        if (carry > 0) {
            result.add(carry);
        }

        return convertList(result);
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