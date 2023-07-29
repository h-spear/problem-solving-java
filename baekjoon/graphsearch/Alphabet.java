// https://www.acmicpc.net/problem/1987

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Alphabet {

    private static final int[] dr = {1, -1, 0, 0};
    private static final int[] dc = {0, 0, 1, -1};
    private static boolean[] visited = new boolean[26];
    private static int R, C;
    private static char[][] graph;
    private static int answer = 0;

    private static void dfs(int r, int c, int cnt) {
        answer = Math.max(answer, cnt);
        int nr, nc, alpha;
        for (int i = 0; i < 4; ++i) {
            nr = r + dr[i];
            nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                continue;
            alpha = graph[nr][nc] - 'A';
            if (visited[alpha])
                continue;
            visited[alpha] = true;
            dfs(nr, nc, cnt + 1);
            visited[alpha] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        for (int i = 0; i < R; ++i) {
            String line = br.readLine();
            for (int j = 0; j < C; ++j) {
                graph[i][j] = line.charAt(j);
            }
        }
        visited[graph[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
