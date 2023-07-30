// https://www.acmicpc.net/problem/4781
// rounding error

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class CandyStore {

    private static int[] c = new int[5001];
    private static int[] p = new int[10001];
    private static int[] dp = new int[10001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n, m;

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
            if (n == 0 && m == 0)
                break;

            initialize(n, m);
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                c[i] = Integer.parseInt(st.nextToken());
                p[i] = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
            }

            for (int i = 0; i <= m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i >= p[j]) {
                        dp[i] = Math.max(dp[i], c[j] + dp[i - p[j]]);
                    }
                }
            }
            bw.write(String.valueOf(dp[m]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void initialize(int n, int m) {
        for (int i = 0; i <= n; ++i)
            c[i] = 0;

        for (int i = 0; i <= m; ++i) {
            p[i] = 0;
            dp[i] = 0;
        }
    }
}
