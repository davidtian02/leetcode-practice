package com.leetcode.problems.medium.alphabet_board_path;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String alphabetBoardPath(String target) {
        Map<Character, int[]> directory = createDirectory();
        StringBuilder result = new StringBuilder();

        char origin = 'a';
        for (int i=0; i<target.length(); i++) {
            char destination = target.charAt(i);
            StringBuilder directions = findDirections(directory, origin, destination).append('!');
            result.append(directions);
            origin = destination;
        }

        return result.toString();
    }

    private Map<Character, int[]> createDirectory() {
        Map<Character, int[]> map = new HashMap<>();
        int row = 0;
        char c = 'a';
        while (c < 'a' + 26) {
            for (int col=0; col<5; col++) {
                map.put(c, new int[]{row, col});
                c++;
            }
            row++;
        }
        return map;
    }

    private StringBuilder findDirections(Map<Character, int[]> directory, char origin, char destination) {
        int[] oc = directory.get(origin);
        int[] dc = directory.get(destination);

        StringBuilder steps = new StringBuilder();

        boolean remainingDown = false;
        if (oc[0] < dc[0]) {
            int dist = (dc[0] - oc[0]);
            if (oc[0] + dist > 4) {
                steps.append(move('D', dist-1));
                remainingDown = true;
            } else {
                steps.append(move('D', dc[0] - oc[0]));
            }
        } else {
            steps.append(move('U', oc[0] - dc[0]));
        }

        if (oc[1] < dc[1]) {
            steps.append(move('R', dc[1] - oc[1]));
        } else {
            steps.append(move('L', oc[1] - dc[1]));
        }

        if (remainingDown) {
            steps.append('D');
        }

        return steps;
    }

    private StringBuilder move(char direction, int distance) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<distance; i++) {
            sb.append(direction);
        }
        return sb;
    }
}
