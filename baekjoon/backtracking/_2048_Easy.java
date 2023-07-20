// https://www.acmicpc.net/problem/12100

package baekjoon.backtracking;

import java.io.*;
import java.util.*;

public class _2048_Easy {

    private static int N, answer = -1;
    private static int[][] graph;
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(graph, 0);
        bw.write("" + answer);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int[][] graph, int depth) {
        if (depth >= 5) {
            answer = Math.max(answer, findMaximum(graph));
            return;
        }
        int[][] moved;
        for (int i = 0; i < 4; ++i) {
            graph = rotateDegree90(graph);
            moved = move(graph);
            dfs(moved, depth + 1);
        }
    }

    private static int findMaximum(int[][] graph) {
        int res = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                res = Math.max(res, graph[i][j]);
            }
        }
        return res;
    }

    private static int[][] move(int[][] graph) {
        int[][] copied = deepCopy(graph);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (copied[i][j] > 0) {
                    queue.add(copied[i][j]);
                    copied[i][j] = 0;
                }
            }
            for (int j = 0; !queue.isEmpty() && j < N; ++j) {
                copied[i][j] = queue.poll();
                if (!queue.isEmpty() && copied[i][j] == queue.peek()) {
                    copied[i][j] <<= 1;
                    queue.poll();
                }
            }
        }
        return copied;
    }

    private static int[][] deepCopy(int[][] graph) {
        int[][] copied = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                copied[i][j] = graph[i][j];
            }
        }
        return copied;
    }

    private static int[][] rotateDegree90(int[][] graph) {
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                rotated[i][j] = graph[N - j - 1][i];
            }
        }
        return rotated;
    }
}
