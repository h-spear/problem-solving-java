// https://www.acmicpc.net/problem/2630

package baekjoon.divideandconquer;

import java.io.*;
import java.util.*;

public class MakingConfetti {

    private static int N;
    private static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] result = dfs(0, 0, N);
        bw.write(result[0] + "\n");
        bw.write(result[1] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] dfs(int x, int y, int l) {
        int[] res = new int[2];
        if (l == 1) {
            res[graph[x][y]] = 1;
            return res;
        }
        int l2 = l >> 1;
        int[] lt = dfs(x, y, l2);
        int[] rt = dfs(x + l2, y, l2);
        int[] lb = dfs(x, y + l2, l2);
        int[] rb = dfs(x + l2, y + l2, l2);
        if (lt[0] == 0 && rt[0] == 0 && lb[0] == 0 && rb[0] == 0) {
            res[1] = 1;
        } else if (lt[1] == 0 && rt[1] == 0 && lb[1] == 0 && rb[1] == 0) {
            res[0] = 1;
        } else {
            res[0] = lt[0] + rt[0] + lb[0] + rb[0];
            res[1] = lt[1] + rt[1] + lb[1] + rb[1];
        }
        return res;
    }
}
