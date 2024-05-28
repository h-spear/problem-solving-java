// https://www.acmicpc.net/problem/14712

package baekjoon.backtracking;

import java.io.*;
import java.util.*;

public class NemmoNemmoEasy {

    private static int N, M;
    private static int[][] graph;
    private static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        dfs(0);
        System.out.println(answer);
        br.close();
    }

    private static void dfs(int index) {
        if (index == N * M) {
            ++answer;
            return;
        }
        int x = index / M;
        int y = index % M;
        if (x == 0 || y == 0 ||
                graph[x - 1][y] != 1 ||
                graph[x][y - 1] != 1 ||
                graph[x - 1][y - 1] != 1) {
            graph[x][y] = 1;
            dfs(index + 1);
        }
        graph[x][y] = 0;
        dfs(index + 1);
    }
}
