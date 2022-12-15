// https://www.acmicpc.net/problem/4354

package baekjoon.kmp;

import java.io.*;

public class StringSquare {

    private int[] failure(String pattern, int n) {
        int[] table = new int[n];

        int j = 0;
        for (int i = 1; i < n; ++i) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
                j = table[j - 1];

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                table[i] = j;
            }
        }
        return table;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = br.readLine();

            if (s.equals("."))
                break;

            int l = s.length();
            int[] table = failure(s, l);
            int n = l - table[l - 1];

            if (l % n == 0) {
                System.out.println(l / n);
            } else {
                System.out.println(1);
            }
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new StringSquare().solution();
    }
}
