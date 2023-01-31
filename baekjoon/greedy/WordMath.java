// https://www.acmicpc.net/problem/1339

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class WordMath {

    class Pair {
        char c;
        int value;

        public Pair(char c, int value) {
            this.c = c;
            this.value = value;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        for (int i = 0; i < n; ++i) {
            words[i] = br.readLine();
        }

        Map<Character, Integer> map = new HashMap<>();
        for (String word: words) {
            int wordLength = word.length();
            for (int i = 0; i < wordLength; ++i) {
                char c = word.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + (int) Math.pow(10, wordLength - i - 1));
            }
        }

        List<Pair> list = new ArrayList<>();
        for (Character c: map.keySet()) {
            list.add(new Pair(c, map.get(c)));
        }
        list.sort((a, b) -> b.value - a.value);

        int num = 9;
        for (Pair p: list) {
            map.put(p.c, num--);
        }

        int answer = 0;
        for (String word: words) {
            int converted = 0;
            int wordLength = word.length();
            for (int i = 0; i < wordLength; ++i) {
                converted += map.get(word.charAt(i)) * (int) Math.pow(10, wordLength - i - 1);
            }
            answer += converted;
        }

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new WordMath().solution();
    }
}
