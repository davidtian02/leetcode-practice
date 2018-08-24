package com.leetcode.addtwonumbers;

class Main {
    public static void main(String... args) {
        ListNode list1 = makeList(new int[]{2, 4, 3});
        ListNode list2 = makeList(new int[]{5, 6, 4});
        new Solution().addTwoNumbers(list1, list2);
    }

    private static ListNode makeList(int[] arr) {
        ListNode root = null;
        ListNode temp = null;
        for (int e : arr) {
            if (temp == null) {
                temp = new ListNode(e);
                root = temp;
            } else {
                temp.next = new ListNode(e);
                temp = temp.next;
            }
        }

        return root;
    }
}
