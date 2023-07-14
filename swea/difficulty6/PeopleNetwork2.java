// 1263

package swea.difficulty6;

import java.io.*;
import java.util.*;

public class PeopleNetwork2 {

    private static final int INF = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            int N, node;
            int[][] graph;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            graph = new int[N][N];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    node = Integer.parseInt(st.nextToken());
                    graph[i][j] = node == 1 ? 1 : INF;
                }
            }

            // floyd
            for (int k = 0; k < N; ++k) {
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < N; ++j) {
                        if (i != j) {
                            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                        }
                    }
                }
            }

            int answer = INF;
            for (int i = 0; i < N; ++i) {
                int temp = 0;
                for (int j = 0; j < N; ++j) {
                    temp += graph[i][j] != INF ? graph[i][j] : 0;
                }
                answer = Math.min(answer, temp);
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
