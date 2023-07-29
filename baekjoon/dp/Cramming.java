// https://www.acmicpc.net/problem/14728

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Cramming {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] K = new int[N + 1];
        int[] S = new int[N + 1];
        int[][] dp = new int[N + 1][10001];
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            K[i] = Integer.parseInt(st.nextToken());
            S[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j <= T; ++j) {
                if (j >= K[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], S[i] + dp[i - 1][j - K[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][T]);
        br.close();
    }
}
