package com.leetcode.challenge.august2020.iterator_for_combination;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/550/week-2-august-8th-august-14th/3422/
class CombinationIterator {

    private Iterator<Integer> bitDataIter;
    private String word;

    public CombinationIterator(String characters, int combinationLength) {
        List<Integer> bitData = new LinkedList<>();
        word = characters;

        int max = 0;
        for (int i=0; i<characters.length(); i++) {
            max = (max << 1) + 1; // no need for paranthesis?
        }
        for (int i=max; i>0; i--) {
            if (Integer.bitCount(i) == combinationLength) {
                bitData.add(i);
            }
        }

        bitDataIter = bitData.iterator();
    }

    public String next() {
        int next = bitDataIter.next();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (next != 0) {
            char c = word.charAt(word.length() - 1 - index);
            if ((next & 0x01) == 1) {
                sb.append(c);
            }
            next >>= 1;
            index++;
        }
        return sb.reverse().toString();
    }

    public boolean hasNext() {
        return bitDataIter.hasNext();
    }

    public static void main(String... args) {
//        CombinationIterator runner = new CombinationIterator("abc", 2);
        CombinationIterator runner = new CombinationIterator("bvwz", 2);
        while (runner.hasNext()) {
            System.out.println(runner.next());
        }
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
