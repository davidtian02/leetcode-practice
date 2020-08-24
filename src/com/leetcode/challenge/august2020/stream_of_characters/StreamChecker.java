package com.leetcode.challenge.august2020.stream_of_characters;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3434/
class StreamChecker {
    private Trie root;
    private StringBuilder previousQueries;

    // you'll use a Trie here, and query like a state machine. one wrong step kicks it off course, BUT you can keep querying after getting one right.
    // NOTE - actually you HAVE to use the reverse order of everything because they made a Time limit for tests.
    public StreamChecker(String[] words) {
        root = new Trie('$');
        previousQueries = new StringBuilder();
        for (String w : words) {
            Trie next = root;
            for (int i=w.length()-1; i>=0; i--) {
                char c = w.charAt(i);
                next = next.addChild(c);
            }
            next.setIsWord();
        }
    }

    public boolean query(char letter) {
        boolean found = false;
        previousQueries.append(letter);
        Trie next = root;
        for (int i=previousQueries.length()-1; i>=0; i--) {
            char c = previousQueries.charAt(i);
            if (next.getChild(c) != null) {
                next = next.getChild(c);
                if (next.isWord()) {
                    found = true;
                    break;
                }
            } else {
                break;
            }
        }

        return found;
    }

    static class Trie {
        private boolean isWord;
        private Map<Character, Trie> children;
        Trie(char c) {
            isWord = false;
            children = new HashMap<>(); // TODO you can improve efficiency later with array only if need be. focus readbility for now
        }

        Trie addChild(char c) {
            Trie t = new Trie(c);
            if (!children.containsKey(c)) {
                children.put(c, t);
                return t;
            } else {
                return children.get(c);
            }
        }

        Trie getChild(char c) {
            return children.get(c);
        }

        void setIsWord() {
            isWord = true;
        }

        boolean isWord() {
            return isWord;
        }
    }


    public static void main(String... args) {
        testCase1();
//        testCase2();
    }

    private static void testCase1() {
        StreamChecker sc = new StreamChecker(new String[]{"cd","f","kl"});
        System.out.println(sc.query('a'));
        System.out.println(sc.query('b'));
        System.out.println(sc.query('c'));
        System.out.println(sc.query('d'));
        System.out.println(sc.query('d'));
        System.out.println(sc.query('e'));
        System.out.println(sc.query('f'));
    }

    private static void testCase2() {
        StreamChecker sc = new StreamChecker(new String[]{"ab","aaab","ba","baa","abab"});
        System.out.println(sc.query('a'));
        System.out.println(sc.query('a'));
        System.out.println(sc.query('a'));
        System.out.println(sc.query('a'));
        System.out.println(sc.query('a'));
        System.out.println(sc.query('a'));
        System.out.println(sc.query('a'));
        System.out.println(sc.query('b'));
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */