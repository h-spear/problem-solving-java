// https://www.acmicpc.net/problem/17845

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Course {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] I = new int[K + 1];
        int[] T = new int[K + 1];
        for (int i = 1; i <= K; ++i) {
            st = new StringTokenizer(br.readLine());
            I[i] = Integer.parseInt(st.nextToken());
            T[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 1; i <= K; ++i) {
            for (int j = 0; j <= N; ++j) {
                if (j >= T[i])
                    dp[i][j] = Math.max(dp[i - 1][j], I[i] + dp[i - 1][j - T[i]]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        bw.write(String.valueOf(dp[K][N]));
        bw.flush();
        bw.close();
        br.close();
    }
}
