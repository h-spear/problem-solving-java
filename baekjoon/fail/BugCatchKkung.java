// https://www.acmicpc.net/problem/6613
// 메모리 초과

package baekjoon.fail;

import java.io.*;
import java.util.*;

public class BugCatchKkung {

    private int[] failure(String pattern, int m) {
        int[] table = new int[m];
        int j = 0;
        for (int i = 1; i < m; ++i) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
                j = table[j - 1];

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                table[i] = j;
            }
        }
        return table;
    }

    private String KMP(String string, String pattern, int[] table) {
        int n = string.length();
        int m = pattern.length();
        StringBuilder sb;

        int i = 0;
        int j = 0;
        while (i < n) {
            if (n < m)
                break;

            while (j > 0 && string.charAt(i) != pattern.charAt(j))
                j = table[j - 1];

            if (string.charAt(i) == pattern.charAt(j)) {
                j++;
                if (j == m) {
                    sb = new StringBuilder(string);
                    string = sb.substring(0, i - m + 1) + sb.substring(i + 1, n);
                    n = string.length();
                    i = Math.max(-1, i - 2 * m);
                    j = 0;
                }
            }
            i++;
        }
        return string;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String input;

        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            int t = Integer.parseInt(st.nextToken());
            String pattern = st.nextToken();
            int[] table = failure(pattern, pattern.length());

            for (int tc = 0; tc < t; ++tc) {
                bw.write(KMP(br.readLine(), pattern, table));
                bw.newLine();
                bw.flush();
            }
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new BugCatchKkung().solution();
    }
}
