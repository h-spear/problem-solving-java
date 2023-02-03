// https://www.acmicpc.net/problem/1256

package baekjoon.combinatorics;

import java.io.*;
import java.util.*;

public class Dictionary {

    private static int N, M, K;
    private static int[][] dp;
    private static int MAX = (int) 1e9;
    private static StringBuilder sb;
    private static boolean find;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder("");

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = makeCombinationDP(N + M);

        find(N + M, M, K);

        if (find) {
            System.out.println(sb.toString());
            // 역으로 변환 테스트
            if (findWordIndex(sb.toString()) != K) {
                throw new Exception("변환 실패");
            }
        } else {
            System.out.println(-1);
        }
        br.close();
    }

    private static int[][] makeCombinationDP(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            dp[i][0] = 1;
            for (int j = 1; j < i; ++j) {
                dp[i][j] = Math.min(dp[i-1][j-1] + dp[i-1][j], MAX);
            }
            dp[i][i] = 1;
        }
        return dp;
    }

    // k : 남은 bucket
    private static void find(int n, int k, int target) {
        if (n == 0) {
            find = true;
            return;
        }

        int aSelect = dp[n-1][k];
        if (target <= aSelect) {
            sb.append('a');
            find(n - 1, k, target);
        } else {
            if (k > 0) {
                sb.append('z');
                find(n - 1, k - 1, target - aSelect);
            }
        }
    }

    private static int findWordIndex(String word) {
        char[] chars = word.toCharArray();
        int n, r = 0;
        for (char c: chars) {
            if (c == 'z') {
                r++;
            }
        }
        int res = 1;
        int wordLength = word.length();
        for (int i = 0; i < wordLength; ++i) {
            n = (wordLength - i - 1);
            if (word.charAt(i) == 'z') {
                res += dp[n][r--];
            }
        }
        return res;
    }
}
