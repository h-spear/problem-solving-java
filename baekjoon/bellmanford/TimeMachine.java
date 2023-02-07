// https://www.acmicpc.net/problem/11657

package baekjoon.bellmanford;

import java.io.*;
import java.util.*;

public class TimeMachine {
    private static final int INF = Integer.MAX_VALUE;
    private static int N, M;
    private static Edge[] edges;
    private static long[] distance;

    static class Edge {
        int s, e, cost;

        public Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int A, B, C;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new long[N + 1];
        edges = new Edge[M];

        for (int i = 0; i <= N; ++i) {
            distance[i] = INF;
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, C);
        }

        if (bellmanFord(1)) {
            bw.write(-1 + "\n");
        } else {
            for (int i = 2; i <= N; ++i) {
                if (distance[i] == INF) {
                    bw.write(-1 + "\n");
                } else {
                    bw.write(distance[i] + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 음의 사이클 여부 리턴
    private static boolean bellmanFord(int start) {
        distance[start] = 0;

        for (int i = 0; i < N; ++i) {
            boolean update = false;
            for (Edge edge: edges) {
                if (distance[edge.s] != INF && distance[edge.e] > distance[edge.s] + edge.cost) {
                    distance[edge.e] = distance[edge.s] + edge.cost;
                    update = true;
                }
            }

            if (i == N - 1 && update) {
                return true;    // 음의 사이클이 존재
            }
        }
        return false;
    }
}
