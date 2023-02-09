// https://www.acmicpc.net/problem/2098
// TSP

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class TravelingSalesmanProblem {

    private static final int INF = Integer.MAX_VALUE / 2;   // 오버플로우
    private int N, fullVisit;
    private int[][] W;
    private int[][] dp;

    private int dfs(int curr, int visited) {
        // 목적지인가
        if (visited == fullVisit) {
            if (W[curr][0] > 0) {
                return W[curr][0];
            } else {
                return INF / 2;
            }
        }

        if (dp[curr][visited] == INF) {
            for (int next = 1; next < N; ++next) {
                if (W[curr][next] == 0)
                    continue;
                if ((visited & (1 << next)) != 0)
                    continue;

                int temp = dfs(next, visited | (1 << next)) + W[curr][next];
                dp[curr][visited] = Math.min(dp[curr][visited], temp);
            }
        }
        return dp[curr][visited];
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        fullVisit = (1 << N) - 1;
        W = new int[N][N];
        dp = new int[N][(int) Math.pow(2, N)];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], INF);
        }

        int res = dfs(0, 1);

        if (res == INF) {
            bw.write(-1 + "\n");
        } else {
            bw.write(res + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new TravelingSalesmanProblem().solution();
    }
}
