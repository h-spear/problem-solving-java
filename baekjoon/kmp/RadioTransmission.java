// https://www.acmicpc.net/problem/3356

package baekjoon.kmp;

import java.io.*;

public class RadioTransmission {

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
        int l = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int[] table = failure(s, l);
        System.out.println(l - table[l - 1]);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new RadioTransmission().solution();
    }
}
