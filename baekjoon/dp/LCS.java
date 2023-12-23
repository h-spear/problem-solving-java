// https://www.acmicpc.net/problem/9251

package baekjoon.dp;

import java.io.*;

public class LCS {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word1 = br.readLine();
        String word2 = br.readLine();
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        System.out.println(dp[n][m]);
        br.close();
    }
}
