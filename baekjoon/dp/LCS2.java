// https://www.acmicpc.net/problem/9252

package baekjoon.dp;

import java.io.*;

public class LCS2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        String B = br.readLine();

        int N = A.length();
        int M = B.length();

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        bw.write(dp[N][M] + "\n");
        if (dp[N][M] != 0) {
            int length = dp[N][M];
            char[] chars = new char[length];
            int i = N, j = M;
            while (i > 0 && j > 0) {
                if (dp[i][j] == length && dp[i-1][j] == length - 1 && dp[i][j-1] == length - 1) {
                    chars[length - 1] = A.charAt(i-1);
                    i--;
                    j--;
                    length--;
                } else {
                    if (dp[i][j-1] > dp[i-1][j]) {
                        j--;
                    } else {
                        i--;
                    }
                }
            }
            bw.write(String.valueOf(chars));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
