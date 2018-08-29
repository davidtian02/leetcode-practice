package com.leetcode.jewelsAndStones;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/submissions/detail/172359423/  which beats 99% of java submissions
class Solution {
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        boolean[] map = new boolean[58];
        int offset = 65;
        for (int i=0; i<J.length(); i++) {
            map[((int)J.charAt(i)) - offset] = true;
        }
        for (int i=0; i<S.length(); i++) {
            if (map[(int)S.charAt(i) - offset]) {
                count++;
            }
        }
        return count;
    }

    // https://leetcode.com/submissions/detail/172358050/  which is 25ms, and only beats 3% of java submissions.
    private int solution2(String J, String S) {
        Set<Character> map = new HashSet<>();
        for (char c : J.toCharArray()) {
            map.add(c);
        }

        int count = 0;
        for (int i=0; i<S.length(); i++) {
            if (map.contains(S.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}