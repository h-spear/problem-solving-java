// https://www.acmicpc.net/problem/14503

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class RoboticVacuum {

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private int directionLeft(int d) {
        return (d + 3) % 4;
    }

    private int directionBack(int d) {
        return (d + 2) % 4;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][m];
        boolean[][] completed = new boolean[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int nr, nc;
        while (true) {
            // 1
            completed[r][c] = true;

            // 2
            boolean find = false;
            for (int i = 0; i < 4; ++i) {
                d = directionLeft(d);
                nr = r + dr[d];
                nc = c + dc[d];

                if (graph[nr][nc] == 1)
                    continue;
                if (completed[nr][nc])
                    continue;

                find = true;
                r = nr;
                c = nc;
                break;
            }

            if (!find) {
                int dBack = directionBack(d);
                nr = r + dr[dBack];
                nc = c + dc[dBack];

                if (graph[nr][nc] == 1)
                    break;

                r = nr;
                c = nc;
            }
        }

        // counting
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (completed[i][j])
                    ++answer;
            }
        }

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new RoboticVacuum().solution();
    }
}
