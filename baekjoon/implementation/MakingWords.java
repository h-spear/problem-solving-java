// https://www.acmicpc.net/problem/1148

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class MakingWords {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int[][] alphabetCounter = new int[200001][26];
        int wordCount = 0;
        while (!(line = br.readLine()).equals("-")) {
            for (char c: line.toCharArray()) {
                ++alphabetCounter[wordCount][c - 'A'];
            }
            ++wordCount;
        }

        StringBuilder sb = new StringBuilder();
        while (!(line = br.readLine()).equals("#")) {
            int[] puzzleCounter = new int[26];
            char[] chars = line.toCharArray();
            for (char c: chars) {
                ++puzzleCounter[c - 'A'];
            }
            List<Integer> filtered = filter(wordCount, alphabetCounter, puzzleCounter);

            int bestCount = 0;
            int worstCount = 9999;
            boolean[] bestAlphabets = new boolean[26];
            boolean[] worstAlphabets = new boolean[26];
            for (char c: chars) {
                int count = 0;
                for (int i: filtered) {
                    if (alphabetCounter[i][c - 'A'] > 0) {
                        ++count;
                    }
                }
                if (count > bestCount) {
                    bestCount = count;
                    Arrays.fill(bestAlphabets, false);
                    bestAlphabets[c - 'A'] = true;
                } else if (count == bestCount) {
                    bestAlphabets[c - 'A'] = true;
                }
                if (count < worstCount) {
                    worstCount = count;
                    Arrays.fill(worstAlphabets, false);
                    worstAlphabets[c - 'A'] = true;
                } else if (count == worstCount) {
                    worstAlphabets[c - 'A'] = true;
                }
            }
            for (int i = 0; i < 26; ++i) {
                if (worstAlphabets[i]) {
                    sb.append((char) (i + 'A'));
                }
            }
            sb.append(" ").append(worstCount)
                    .append(" ");
            for (int i = 0; i < 26; ++i) {
                if (bestAlphabets[i]) {
                    sb.append((char) (i + 'A'));
                }
            }
            sb.append(" ").append(bestCount)
                    .append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static List<Integer> filter(int wordCount, int[][] alphabetCounter, int[] puzzleCounter) {
        List<Integer> filtered = new ArrayList<>();
        for (int i = 0; i < wordCount; ++i) {
            boolean filter = true;
            for (int j = 0; j < 26; ++j) {
                if (alphabetCounter[i][j] > puzzleCounter[j]) {
                    filter = false;
                    break;
                }
            }
            if (filter) {
                filtered.add(i);
            }
        }
        return filtered;
    }
}
