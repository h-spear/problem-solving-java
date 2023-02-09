// https://www.acmicpc.net/problem/1915

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class LargestSquare {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int max = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(line.substring(j, j + 1));
                if (arr[i][j] == 1) {
                    max = 1;
                }
            }
        }

        // dp
        for (int i = 0; i < n; ++i) {
            dp[i][0] = arr[i][0];
        }
        for (int j = 0; j < m; ++j) {
            dp[0][j] = arr[0][j];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (arr[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        bw.write("" + max * max);
        bw.flush();
        bw.close();
        br.close();
    }
}
