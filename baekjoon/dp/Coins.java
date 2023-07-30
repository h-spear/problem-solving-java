// https://www.acmicpc.net/problem/3067

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Coins {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int N, M, answer;
        int[] coins;
        int[][] dp;

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            dp = new int[N + 1][M + 1];

            for (int i = 0; i < N; ++i) {
                dp[i][coins[i]] = 1;
            }

            for (int j = 1; j <= M; ++j) {
                for (int i = 0; i < N; ++i) {
                    for (int k = 0; k <= i; ++k) {
                        if (j >= coins[i])
                            dp[i][j] += dp[i - k][j - coins[i]];
                    }
                }
            }

            answer = 0;
            for (int i = 0; i < N; ++i) {
                answer += dp[i][M];
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
