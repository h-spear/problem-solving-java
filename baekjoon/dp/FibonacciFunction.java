// https://www.acmicpc.net/problem/1003

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class FibonacciFunction {

    private static int[][] dp = new int[41][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        dp[0][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= 40; ++i) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            bw.write(dp[N][0] + " " + dp[N][1]);
            bw.newLine();
        }


        bw.flush();
        bw.close();
        br.close();
    }
}
