package com.leetcode.problems.medium.implement_trie_prefix_tree;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/implement-trie-prefix-tree/
class Trie {

    private Map<Character, Trie> data; // TODO consider char array if need to optimize
    private boolean isWord;

    /** Initialize your data structure here. */
    public Trie() {
        data = new HashMap<>();
        isWord = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (int i=0,len=word.length(); i<len; i++) {
            char c = word.charAt(i);
            if (node.data.containsKey(c)) {
                node = node.data.get(c);
            } else {
                Trie nn = new Trie();
                node.data.put(c, nn);
                node = nn;
            }
        }
        node.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = this;
        for (int i=0,len=word.length(); i<len; i++) {
            char c = word.charAt(i);
            if (!node.data.containsKey(c)) {
                return false;
            }
            node = node.data.get(c);
        }
        return node.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = this;
        for (int i=0,len=prefix.length(); i<len; i++) {
            char c = prefix.charAt(i);
            if (!node.data.containsKey(c)) {
                return false;
            }
            node = node.data.get(c);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
