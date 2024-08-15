// https://www.acmicpc.net/problem/16724

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class PiedPiper {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int N, M;
    private static char[][] graph;
    private static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                graph[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0, id = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (visited[i][j] == 0)
                    answer += dfs(i, j, ++id);
            }
        }
        System.out.println(answer);
        br.close();
    }

    private static int dfs(int x, int y, int id) {
        visited[x][y] = id;
        int i = getDirection(graph[x][y]);
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (visited[nx][ny] == 0)
            return dfs(nx, ny, id);
        else if (visited[nx][ny] == id)
            return 1;
        else return 0;
    }

    private static int getDirection(char c) {
        if (c == 'U') return 0;
        else if (c == 'D') return 1;
        else if (c == 'L') return 2;
        else if (c == 'R') return 3;
        else return -1;
    }
}
