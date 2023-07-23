// https://www.acmicpc.net/problem/20920

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class HardToMemorizeEnglishWords {

    static class Pair {
        String word;
        int count;

        public Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    private static Map<String, Integer> map = new HashMap<>(200003);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; ++i) {
            String word = br.readLine();
            if (word.length() >= M) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        List<Pair> words = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            words.add(new Pair(entry.getKey(), entry.getValue()));
        }

        words.sort((o1, o2) -> {
            if (o1.count != o2.count) {
                return o2.count - o1.count;
            }
            if (o1.word.length() != o2.word.length()) {
                return o2.word.length() - o1.word.length();
            }
            return o1.word.compareTo(o2.word);
        });

        for (Pair pair: words) {
            bw.write(pair.word);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
