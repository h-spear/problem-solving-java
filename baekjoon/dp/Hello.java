// https://www.acmicpc.net/problem/1535

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Hello {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] L = new int[N + 1];
        int[] J = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            J[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][100];
        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j <= 99; ++j) {
                if (j >= L[i])
                    dp[i][j] = Math.max(dp[i - 1][j], J[i] + dp[i - 1][j - L[i]]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        bw.write("" + dp[N][99]);

        bw.flush();
        bw.close();
        br.close();
    }
}
