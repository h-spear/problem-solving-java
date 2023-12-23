// https://www.acmicpc.net/problem/2225

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class SumDecomposition {

    private static final int MOD = 1_000_000_000;
    private static int[][] mem;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        mem = new int[N + 1][K + 1];
        for (int i = 0; i <= N; ++i) {
            Arrays.fill(mem[i], -1);
        }
        System.out.println(dfs(N, K));
        br.close();
    }

    private static int dfs(int n, int k) {
        if (k == 0)
            return n == 0 ? 1 : 0;
        if (mem[n][k] != -1)
            return mem[n][k];

        mem[n][k] = 0;
        for (int i = 0; i <= n; ++i) {
            mem[n][k] += dfs(n - i, k - 1);
            mem[n][k] %= MOD;
        }
        return mem[n][k];
    }
}
