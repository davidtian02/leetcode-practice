package com.leetcode.challenge.august2020.design_hashset;

class MyHashSet {

    private boolean[] container;
    /** Initialize your data structure here. */
    public MyHashSet() {
        container = new boolean[1000001];
    }

    public void add(int key) {
        container[key] = true;
    }

    public void remove(int key) {
        container[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return container[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */