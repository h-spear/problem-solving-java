// https://www.acmicpc.net/problem/7579

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class App {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = new int[N + 1];
        int[] cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        int maxCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i];
        }

        int minCost = Integer.MAX_VALUE;
        int[][] dp = new int[N + 1][maxCost + 1];
        for (int i = 1; i <= N; ++i) {
            for (int c = 0; c <= maxCost; ++c) {
                if (c - cost[i] >= 0) {
                    dp[i][c] = Math.max(dp[i-1][c], dp[i-1][c - cost[i]] + memory[i]);
                } else {
                    dp[i][c] = dp[i-1][c];
                }

                if (dp[i][c] >= M) {
                    minCost = Math.min(minCost, c);
                    break;
                }
            }
        }

        bw.write("" + minCost);
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new App().solution();
    }
}
