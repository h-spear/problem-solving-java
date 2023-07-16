// https://www.acmicpc.net/problem/7579

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class App {

    private static final int C = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] m = new int[N + 1];
        int[] c = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N + 1][C + 1];
        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j <= C; ++j) {
                if (j >= c[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], m[i] + dp[i - 1][j - c[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int answer = 0;
        for (int j = 0; j <= C; ++j) {
            if (dp[N][j] >= M) {
                answer = j;
                break;
            }
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }
}
