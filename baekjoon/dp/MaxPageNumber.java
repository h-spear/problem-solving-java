// https://www.acmicpc.net/problem/16493

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class MaxPageNumber {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] D = new int[M + 1];
        int[] P = new int[M + 1];
        for (int i = 1; i <= M; ++i) {
            st = new StringTokenizer(br.readLine());
            D[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; ++i) {
            for (int j = 0; j <= N; ++j) {
                if (j >= D[i])
                    dp[i][j] = Math.max(dp[i - 1][j], P[i] + dp[i - 1][j - D[i]]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        bw.write(String.valueOf(dp[M][N]));
        bw.flush();
        bw.close();
        br.close();
    }
}
