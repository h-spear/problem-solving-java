// https://www.acmicpc.net/problem/5582

package baekjoon.dp;

import java.io.*;

public class CommonSubstring {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N, M, maxLength = 0;
        int[][] dp;

        String B = br.readLine();
        String A = br.readLine();

        N = A.length();
        M = B.length();
        dp = new int[N][M];

        for (int j = 0; j < M; ++j) {
            if (A.charAt(0) == B.charAt(j)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = (j == 0 ? 0 : dp[0][j - 1]);
            }
        }
        for (int i = 0; i < N; ++i) {
            if (A.charAt(i) == B.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = (i == 0 ? 0 : dp[i - 1][0]);
            }
        }
        for (int i = 1; i < N; ++i) {
            for (int j = 1; j < M; ++j) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        bw.write(maxLength + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new CommonSubstring().solution();
    }
}
