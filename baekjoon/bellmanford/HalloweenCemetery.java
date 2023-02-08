// https://www.acmicpc.net/problem/3860

package baekjoon.bellmanford;

import java.io.*;
import java.util.*;

public class HalloweenCemetery {

    private static final long INF = Long.MAX_VALUE;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static int N, W, H;
    private static boolean[][] isGrave;
    private static long[][] distance;
    private static Hole[][] hole;

    class Hole {
        int y, x, t;

        public Hole(int y, int x, int t) {
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int X, Y, X1, Y1, X2, Y2, T, G, E;

        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if (H == 0 && W == 0) {
                break;
            }

            // init
            N = H * W;
            isGrave = new boolean[H][W];
            distance = new long[H][W];
            hole = new Hole[H][W];

            for (int y = 0; y < H; ++y) {
                for (int x = 0; x < W; ++x) {
                    distance[y][x] = INF;
                }
            }

            G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; ++i) {
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                isGrave[Y][X] = true;
            }

            E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; ++i) {
                st = new StringTokenizer(br.readLine());
                X1 = Integer.parseInt(st.nextToken());
                Y1 = Integer.parseInt(st.nextToken());
                X2 = Integer.parseInt(st.nextToken());
                Y2 = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
                hole[Y1][X1] = new Hole(Y2, X2, T);
            }

            // bellman ford
            boolean isCycle = bellmanFord();
            if (isCycle) {
                bw.write("Never\n");
            } else if (distance[H-1][W-1] == INF) {
                bw.write("Impossible\n");
            } else {
                bw.write(distance[H-1][W-1] + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bellmanFord() {
        int ny, nx;
        boolean update;

        distance[0][0] = 0;

        for (int k = 0; k < N; ++k) {
            update = false;

            for (int y = 0; y < H; ++y) {
                for (int x = 0; x < W; ++x) {
                    if (y == H - 1 && x == W - 1)
                        continue;
                    if (distance[y][x] == INF)
                        continue;

                    if (hole[y][x] != null) {
                        Hole h = hole[y][x];
                        if (distance[h.y][h.x] > distance[y][x] + h.t) {
                            distance[h.y][h.x] = distance[y][x] + h.t;
                            update = true;
                        }
                    } else {
                        for (int i = 0; i < 4; ++i) {
                            ny = y + dy[i];
                            nx = x + dx[i];

                            if (ny < 0 || nx < 0 || ny >= H || nx >= W)
                                continue;
                            if (isGrave[ny][nx])
                                continue;

                            if (distance[ny][nx] > distance[y][x] + 1) {
                                distance[ny][nx] = distance[y][x] + 1;
                                update = true;
                            }
                        }
                    }
                }
            }
            if (k == N - 1 && update) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        new HalloweenCemetery().solution();
    }
}
