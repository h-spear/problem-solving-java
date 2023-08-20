// https://www.acmicpc.net/problem/17135

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class CastleDefense {

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {0, -1, 0};
    private static final int[] dy = {-1, 0, 1};

    private static int N, M, D, answer;
    private static boolean[][] graph;
    private static boolean[][] orgGraph;
    private static int[] comb;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        graph = new boolean[N][M];
        orgGraph = new boolean[N][M];
        visited = new boolean[N][M];
        comb = new int[3];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
                orgGraph[i][j] = graph[i][j];
            }
        }

        dfs(0, 0);
        System.out.println(answer);
        br.close();
    }

    private static void dfs(int idx, int depth) {
        if (depth == 3) {
            deepCopy(orgGraph, graph);
            answer = Math.max(answer, simul());
            return;
        }
        for (int i = idx; i < M; ++i) {
            comb[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }

    private static int simul() {
        int res = 0;
        for (int x = N - 1; x >= 0; --x) {
            List<Pair> removed = new ArrayList<>();
            for (int i = 0; i < 3; ++i)
                removed.add(bfs(x, comb[i], D));

            for (Pair p: removed) {
                if (p != null && graph[p.x][p.y]) {
                    graph[p.x][p.y] = false;
                    ++res;
                }
            }
        }
        return res;
    }

    private static Pair bfs(int x, int y, int distance) {
        for (int i = 0; i <= x; ++i)
            Arrays.fill(visited[i], false);

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;

        int nx, ny, queueSize;
        while (!queue.isEmpty() && distance > 0) {
            queueSize = queue.size();
            for (int q = 0; q < queueSize; ++q) {
                Pair p = queue.poll();

                if (graph[p.x][p.y])
                    return new Pair(p.x, p.y);

                for (int i = 0; i < 3; ++i) {
                    nx = p.x + dx[i];
                    ny = p.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            --distance;
        }
        return null;
    }

    private static void deepCopy(boolean[][] from, boolean[][] to) {
        for (int i = 0; i < N; ++i) {
            to[i] = Arrays.copyOf(from[i], M);
        }
    }
}
