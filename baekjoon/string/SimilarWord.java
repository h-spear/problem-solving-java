// https://www.acmicpc.net/problem/2607

package baekjoon.string;

import java.io.*;

public class SimilarWord {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int wordCount = Integer.parseInt(br.readLine());
        String firstWord = br.readLine();
        int[] firstWordCounter = getCounter(firstWord);

        int answer = 0;
        for (int i = 1; i < wordCount; ++i) {
            int[] wordCounter = getCounter(br.readLine());
            if (isSimilar(firstWordCounter, wordCounter)) {
                ++answer;
            }
        }
        System.out.println(answer);
        br.close();
    }

    private static boolean isSimilar(int[] counter1, int[] counter2) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < 26; ++i) {
            int diff = Math.min(counter1[i], counter2[i]);
            sum1 += counter1[i] - diff;
            sum2 += counter2[i] - diff;
        }
        return sum1 <= 1 && sum2 <=1;
    }

    private static int[] getCounter(String word) {
        int[] counter = new int[26];
        for (char c: word.toCharArray()) {
            ++counter[c - 'A'];
        }
        return counter;
    }
}
