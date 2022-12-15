// https://www.acmicpc.net/problem/1786

package baekjoon.kmp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Find {

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

    private List<Integer> KMP(String string, String pattern, int n, int m) {
        int[] table = failure(pattern, m);
        List<Integer> matched = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && string.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            if (string.charAt(i) == pattern.charAt(j)) {
                j++;
                if (j == m) {
                    matched.add(i - m + 2);
                    j = table[j - 1];
                }
            }
        }
        return matched;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String t = br.readLine();
        String p = br.readLine();

        int n = t.length();
        int m = p.length();

        List<Integer> matchIndexes = KMP(t, p, n, m);

        bw.write("" + matchIndexes.size());
        bw.newLine();

        for (Integer idx: matchIndexes) {
            bw.write("" + idx);
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Find().solution();
    }
}
