// https://www.acmicpc.net/problem/2294

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Coin2 {

    private static final int INF = Integer.MAX_VALUE >> 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        for (int i = 0; i < n; ++i) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int j = 0; j <= k; ++j) {
            for (int i = 0; i < n; ++i) {
                if (j - coins[i] >= 0)
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        System.out.println(dp[k] == INF ? -1 : dp[k]);
        br.close();
    }
}
