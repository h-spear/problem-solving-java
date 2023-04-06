// https://www.acmicpc.net/problem/1956

package baekjoon.floydwarshall;

import java.io.*;
import java.util.*;

public class Exercise {

    private static final int INF = 1000000000;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] graph = new int[V+1][V+1];
        for (int i = 0; i <= V; ++i) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
        }

        // floyd
        for (int k = 1; k <= V; ++k) {
            for (int i = 1; i <= V; ++i) {
                for (int j = 1; j <= V; ++j) {
                    if (graph[i][j] >= graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int answer = INF;
        for (int i = 1; i <= V; ++i) {
            for (int j = 1; j <= V; ++j) {
                answer = Math.min(answer, graph[i][j] + graph[j][i]);
            }
        }

        if (answer == INF) {
            bw.write("-1");
        } else {
            bw.write("" + answer);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Exercise().solution();
    }
}
