package com.leetcode.problems.medium.copy_list_with_random_pointer;

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Integer> nodeToIndexMap = new HashMap<>();
        ArrayList<Node> list = new ArrayList<>();
        Node temp = head;
        Node previous = null;
        int index = 0;
        while (temp != null) {
            Node newNode = new Node(temp.val);
            if (previous != null) {
                previous.next = newNode;
            }
            previous = newNode;
            list.add(newNode);
            nodeToIndexMap.put(temp, index);
            temp = temp.next;
            index++;
        }


        // then put random
        Node newTemp = list.get(0);
        temp = head;
        int rIndex;
        while (newTemp != null) {
            Node rnd = temp.random;
            if (rnd != null) {
                rIndex = nodeToIndexMap.get(rnd);
                newTemp.random = list.get(rIndex);
            }
            newTemp = newTemp.next;
            temp = temp.next;
        }

        return list.get(0);
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        Solution runner = new Solution();
        Node n1 = new Node(0);
        Node n2 = new Node(5);
        Node n3 = new Node(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = null;
        n1.random = n1;
        n2.random = null;
        n3.random = n1;
        Node resultHead = runner.copyRandomList(n1);
        System.out.println("done");
    }

    private static void test2() {
        Solution runner = new Solution();
        Node resultHead = runner.copyRandomList(null);
        System.out.println("done");
    }

    private static void test3() {
        Solution runner = new Solution();
        Node n1 = new Node(-100);
        Node resultHead = runner.copyRandomList(n1);
        System.out.println("done");
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}