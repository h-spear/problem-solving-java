// https://www.acmicpc.net/problem/11403

package baekjoon.floydwarshall;

import java.io.*;
import java.util.*;

public class FindPath {
    private static final int INF = 1000000000;

    private void solution() throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;

         int n = Integer.parseInt(br.readLine());
         int[][] graph = new int[n][n];
         for (int i = 0; i < n; ++i)
             for (int j = 0; j < n; ++j)
                 graph[i][j] = INF;

         for (int i = 0; i < n; ++i) {
             st = new StringTokenizer(br.readLine());
             for (int j = 0; j < n; ++j) {
                 if (Integer.parseInt(st.nextToken()) == 1)
                     graph[i][j] = 1;
             }
         }

         for (int k = 0; k < n; ++k) {
             for (int i = 0; i < n; ++i) {
                 for (int j = 0; j < n; ++j) {
                     if (graph[i][k] == 1 && graph[k][j] == 1) {
                         graph[i][j] = 1;
                     }
                 }
             }
         }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (graph[i][j] == INF)
                    System.out.printf("0 ");
                else
                    System.out.printf("1 ");
            }
            System.out.println();
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new FindPath().solution();
    }
}
