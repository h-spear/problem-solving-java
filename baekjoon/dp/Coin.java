// https://www.acmicpc.net/problem/9084

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Coin {

    // 1차원 배열로 풀이 가능
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N, M;
        int[] coins, dp;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            coins = new int[N];
            for (int i = 0; i < N; ++i) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());

            dp = new int[M + 1];
            dp[0] = 1;
            for (int i = 0; i < N; ++i) {
                for (int j = coins[i]; j <= M; ++j) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            bw.write("" + dp[M]);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void mySolution(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N, M, answer;
        int[] coins;
        int[][] dp;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            coins = new int[N + 1];
            for (int i = 1; i <= N; ++i) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());

            dp = new int[N + 1][10001];
            for (int i = 1; i <= N; ++i) {
                dp[i][coins[i]] = 1;
            }

            for (int j = 1; j <= M; ++j) {
                for (int i = 1; i <= N; ++i) {
                    if (j >= coins[i]) {
                        for (int k = 0; k <= i; ++k) {
                            dp[i][j] += dp[k][j - coins[i]];
                        }
                    }
                }
            }

            answer = 0;
            for (int i = 0; i <= N; ++i) {
                answer += dp[i][M];
            }
            bw.write("" + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
