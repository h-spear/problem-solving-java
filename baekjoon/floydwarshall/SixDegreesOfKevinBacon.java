// https://www.acmicpc.net/problem/1389

package baekjoon.floydwarshall;

import java.io.*;
import java.util.*;

public class SixDegreesOfKevinBacon {
    private static final int INF = 1000000000;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n+1][n+1];

        // init
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = INF;
            }
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        // floyd warshall
        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int answer = 0;
        int minima = INF;

        for (int i = 1; i <= n; ++i) {
            int summation = Arrays.stream(graph[i]).sum();
            if (summation < minima) {
                minima = summation;
                answer = i;
            }
        }

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new SixDegreesOfKevinBacon().solution();

    }
}
