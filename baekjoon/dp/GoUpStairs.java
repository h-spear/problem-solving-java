// https://www.acmicpc.net/problem/2579

package baekjoon.dp;

import java.io.*;

public class GoUpStairs {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[][] dp = new int[3][N];
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int c = 0; c < N; ++c) {
            if (c - 2 >= 0) {
                dp[1][c] = Math.max(dp[1][c-2], dp[2][c-2]) + arr[c];
            } else {
                dp[1][c] = arr[c];
            }

            if (c - 1 >= 0) {
                dp[2][c] = dp[1][c-1] + arr[c];
            }
        }

        bw.write(Math.max(dp[1][N-1], dp[2][N-1]) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new GoUpStairs().solution();
    }
}
