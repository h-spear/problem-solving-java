// https://www.acmicpc.net/problem/18428

package baekjoon.backtracking;

import java.io.*;
import java.util.*;

public class AvoidSurveillance {

    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};

    private static int N, N_square;
    private static boolean findAnswer = false;
    private static char[][] graph = new char[6][6];
    private static boolean[][] obstacle = new boolean[6][6];
    private static List<Pair> teachers = new ArrayList<>();

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void dfs(int n, int depth) {
        if (findAnswer) {
            return;
        }
        if (depth == 3) {
            findAnswer |= isAllAvoidable();
            return;
        }

        for (int i = n; i < N_square; ++i) {
            int r = i / N;
            int c = i % N;
            if (graph[r][c] == 'X') {
                obstacle[r][c] = true;
                dfs(i + 1, depth + 1);
                obstacle[r][c] = false;
            }
        }
    }

    private boolean isAllAvoidable() {
        int nx, ny;
        for (Pair t: teachers) {
            for (int i = 0; i < 4; ++i) {
                nx = t.x + dx[i];
                ny = t.y + dy[i];
                while (nx >= 0 && ny >= 0 && nx < N && ny < N && obstacle[nx][ny] == false) {
                    if (graph[nx][ny] == 'S') {
                        return false;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }
        return true;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        N_square = N * N;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[i][j] = st.nextToken().charAt(0);
                if (graph[i][j] == 'T') {
                    teachers.add(new Pair(i, j));
                }
            }
        }
        dfs(0, 0);

        if (findAnswer) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new AvoidSurveillance().solution();
    }
}
