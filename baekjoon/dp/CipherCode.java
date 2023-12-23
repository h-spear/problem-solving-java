// https://www.acmicpc.net/problem/2011

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class CipherCode {

    private static final int P = 1_000_000;
    private static int n;
    private static int[] cipherText;
    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        n = input.length();

        cipherText = new int[n];
        dp = new int[n];
        for (int i = 0; i < n; ++i) {
            cipherText[i] = input.charAt(i) - '0';
        }
        Arrays.fill(dp, -1);
        System.out.println(dfs(0));
        br.close();
    }

    private static int dfs(int i) {
        if (i == n)
            return 1;
        else if (i > n)
            return 0;
        else if (dp[i] != -1)
            return dp[i];
        else if (cipherText[i] == 0)
            return dp[i] = 0;

        dp[i] = 0;
        dp[i] += dfs(i + 1);
        if (i < n - 1) {
            int alpha = cipherText[i] * 10 + cipherText[i + 1];
            if (0 < alpha && alpha < 27) {
                dp[i] += dfs(i + 2);
            }
        }
        dp[i] %= P;
        return dp[i];
    }
}
