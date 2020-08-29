package com.leetcode.problems.medium.lru_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// https://leetcode.com/problems/lru-cache/
class LRUCache {
    private int capacity;
    private Map<Integer, Node> mapping;
    private Node head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        mapping = new HashMap<>();
    }

    public int get(int key) {
        final int val;

        if (mapping.containsKey(key)) {
            Node node = mapping.get(key);
            val = node.val;
            updateRecencyOfExisting(node);
        } else {
            val = -1;
        }

        return val;
    }

    public void put(int key, int value) {
        final Node node;
        final boolean isNew;
        if (mapping.containsKey(key)) {
            node = mapping.get(key);
            node.val = value;
            isNew = false;
        } else {
            node = new Node(key, value);
            mapping.put(key, node);
            isNew = true;
        }

        updateLinks(node, isNew);
    }

    private void updateLinks(Node node, boolean isNew) {
        if (capacity < 1) {
            return;
        }

        if (head == null) { // no elements
            head = node;
            tail = node;
            return;
        }

        if (mapping.size() > capacity) { // evict
            // add new node
            tail.down = node;
            node.up = tail;
            tail = node;

            // evict head
            int keyToEvict = head.key;
            Node temp = head.down; // this for sure exists, and has to be over 1 elements long
            temp.up = null;
            head.down = null;
            head = temp;

            mapping.remove(keyToEvict);
            return;
        } else {
            if (isNew) {
                tail.down = node;
                node.up = tail;
                tail = node;
                return;
            } else {
                updateRecencyOfExisting(node);
            }
        }
    }

    private void updateRecencyOfExisting(Node node) {
        if (tail == node) {
            return; // dont need to do anything if it's already last one
        } else if (head == node) { // can't be only 1 element otherwise head == tail
            node = head.down;
            head.up = tail;
            node.up = null;
            head.down = null;
            tail.down = head;

            tail = head;
            head = node;
        } else { // some element in between
            Node tempUp = node.up;
            Node tempDown = node.down;
            tempUp.down = tempDown;
            tempDown.up = tempUp;
            node.down = null;
            node.up = tail;
            tail.down = node;
            tail = node;
        }
    }

    static class Node {
        Node up, down;
        int key, val;
        Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    public static void main(String... args) {
//        test1();
        test2();
    }

    private static void test1() {
        LRUCache cache = new LRUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    private static void test2() {
        LRUCache cache = new LRUCache( 1 /* capacity */ );

        cache.put(2, 1);
        System.out.println(cache.get(2));       // returns 1
        cache.put(3, 2);    // evicts 2
        System.out.println(cache.get(2));       // returns -1
        System.out.println(cache.get(3));       // returns 2

//        ["LRUCache","put","get","put","get","get"]
//        [[1],[2,1],[2],[3,2],[2],[3]]
    }
}