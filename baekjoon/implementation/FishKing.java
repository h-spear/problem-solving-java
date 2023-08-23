// https://www.acmicpc.net/problem/17143

package baekjoon.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FishKing {

    private static class Shark {
        int s, d, z;

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    // U, D, R, L
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int R, C;
    private static Shark[][] graph;
    private static Shark[][] graph2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int M, r, c, s, d, z;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new Shark[R][C];
        graph2 = new Shark[R][C];

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken()) - 1;
            z = Integer.parseInt(st.nextToken());
            graph[r][c] = new Shark(s, d, z);
        }

        int answer = 0;
        for (int fisher = 0; fisher < C; ++fisher) {
            answer += hook(fisher);
            move();
        }
        System.out.println(answer);
        br.close();
    }

    private static int hook(int col) {
        int size = 0;
        for (int i = 0; i < R; ++i) {
            if (graph[i][col] != null) {
                size = graph[i][col].z;
                graph[i][col] = null;
                break;
            }
        }
        return size;
    }

    private static void move() {
        int nx, ny, move;
        Shark shark;
        for (int x = 0; x < R; ++x) {
            for (int y = 0; y < C; ++y) {
                if (graph[x][y] == null)
                    continue;
                shark = graph[x][y];
                graph[x][y] = null;
                nx = x;
                ny = y;
                move = shark.s;
                while (move-- > 0) {
                    nx += dx[shark.d];
                    ny += dy[shark.d];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                        shark.d ^= 1;
                        nx += 2 * dx[shark.d];
                        ny += 2 * dy[shark.d];
                    }
                }
                graph2[nx][ny] = graph2[nx][ny] == null ?
                                shark : getHighPriority(graph2[nx][ny], shark);
            }
        }
        Shark[][] temp = graph;
        graph = graph2;
        graph2 = temp;
    }

    private static Shark getHighPriority(Shark shark1, Shark shark2) {
        return shark1.z > shark2.z ? shark1 : shark2;
    }
}
