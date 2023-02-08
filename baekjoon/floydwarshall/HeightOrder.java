// https://www.acmicpc.net/problem/2458

package baekjoon.floydwarshall;

import java.io.*;
import java.util.*;

public class HeightOrder {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }

        for (int k = 1; k <= N; ++k) {
            for (int i = 1; i <= N; ++i) {
                for (int j = 1; j <= N; ++j) {
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                    }
                }
            }
        }

        int[] counter = new int[N + 1];
        for (int k = 1; k <= N; ++k) {
            for (int i = 1; i <= N; ++i) {
                if (graph[i][k] == 1 || graph[k][i] == 1) {
                    counter[i]++;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; ++i) {
            if (counter[i] == N - 1) {
                answer++;
            }
        }
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
