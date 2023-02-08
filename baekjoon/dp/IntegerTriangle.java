// https://www.acmicpc.net/problem/1932

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class IntegerTriangle {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n][n];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; ++j) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = triangle[0][0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            for (int j = 1; j <= i; ++j) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }

        int max = 0;
        for(int j = 0; j < n; ++j) {
            max = Math.max(max, dp[n - 1][j]);
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
