// https://www.acmicpc.net/problem/1102

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class PowerPlant {

    private static final int INF = Integer.MAX_VALUE;
    private int N, P;
    private int[][] cost;
    private int[] dp;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[(int) Math.pow(2, N)];

        Arrays.fill(dp, INF);

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int visited = 0;
        char[] flag = br.readLine().toCharArray();
        for (int i = 0; i < N; ++i) {
            if (flag[i] == 'Y') {
                visited |= (1 << i);
            }
        }

        P = Integer.parseInt(br.readLine());

        int res = dfs(visited);
        if (res == INF) {
            bw.write("-1\n");
        } else {
            bw.write(res + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private int dfs(int visited) {
        if (dp[visited] != INF) {
            return dp[visited];
        }
        if (countOnFlag(visited) >= P) {
            return 0;
        } else {
            for (int curr = 0; curr < N; ++curr) {
                if ((visited & (1 << curr)) == 0)  // 꺼져있으면 안됨
                    continue;
                for (int next = 0; next < N; ++next) {
                    if (curr == next)
                        continue;
                    if ((visited & (1 << next)) != 0) // 이미 켜져있는 것을 볼 필요 x
                        continue;

                    int temp = dfs(visited | (1 << next)) + cost[curr][next];
                    dp[visited] = Math.min(dp[visited], temp);
                }
            }
        }
        return dp[visited];
    }

    private int countOnFlag(int visited) {
        int count = 0;
        while (visited > 0) {
            count += (visited & 1);
            visited >>= 1;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        new PowerPlant().solution();
    }
}
