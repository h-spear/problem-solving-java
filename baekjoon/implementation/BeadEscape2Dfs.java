// https://www.acmicpc.net/problem/13460

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class BeadEscape2Dfs {

    // up, right, down, left
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static final int INF = Integer.MAX_VALUE;
    private static int n, m;
    private static char[][] graph;
    private static int answer = INF;

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Balls {
        Pair blue;
        Pair red;

        public Balls(Pair blue, Pair red) {
            this.blue = blue;
            this.red = red;
        }
    }

    private Pair move(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while (nx >= 0 && ny >= 0 && nx < n && ny < m && graph[nx][ny] == '.') {
            nx += dx[dir];
            ny += dy[dir];
        }

        if (graph[nx][ny] == 'O') {
            return null;
        } else if (nx - dx[dir] == x && ny - dy[dir] == y) {
            return new Pair(x, y);
        } else {
            return new Pair(nx - dx[dir], ny - dy[dir]);
        }
    }

    private Balls moveRedFirst(int dir, Pair blue, Pair red) {
        Pair nRed = move(red.x, red.y, dir);
        Pair nBlue = move(blue.x, blue.y, dir);
        if (nRed != null && nBlue != null && nRed.x == nBlue.x && nRed.y == nBlue.y) {
            nBlue = new Pair(nBlue.x - dx[dir], nBlue.y - dy[dir]);
        }
        return new Balls(nBlue, nRed);
    }

    private Balls moveBlueFirst(int dir, Pair blue, Pair red) {
        Pair nBlue = move(blue.x, blue.y, dir);
        Pair nRed = move(red.x, red.y, dir);
        if (nRed != null && nBlue != null && nRed.x == nBlue.x && nRed.y == nBlue.y) {
            nRed = new Pair(nRed.x - dx[dir], nRed.y - dy[dir]);
        }
        return new Balls(nBlue, nRed);
    }

    private Balls tilt(Balls balls, int dir) {
        Pair red = balls.red;
        Pair blue = balls.blue;
        Balls res;
        if (dir == 0 && blue.y == red.y) {
            if (blue.x < red.x) {
                res = moveBlueFirst(dir, blue, red);
            } else {
                res = moveRedFirst(dir, blue, red);
            }
        } else if (dir == 1 && blue.x == red.x) {
            if (blue.y < red.y) {
                res = moveRedFirst(dir, blue, red);
            } else {
                res = moveBlueFirst(dir, blue, red);
            }
        } else if (dir == 2 && blue.y == red.y) {
            if (blue.x < red.x) {
                res = moveRedFirst(dir, blue, red);
            } else {
                res = moveBlueFirst(dir, blue, red);
            }
        } else if (dir == 3 && blue.x == red.x) {
            if (blue.y < red.y) {
                res = moveBlueFirst(dir, blue, red);
            } else {
                res = moveRedFirst(dir, blue, red);
            }
        } else {
            res = moveBlueFirst(dir, blue, red);
        }
        return res;
    }

    private boolean isGameClear(Balls balls) {
        if (balls.red == null && balls.blue != null)
            return true;
        return false;
    }

    private void dfs(Balls balls, int depth) {
        if (depth >= answer || depth == 11 || balls.blue == null)
            return;
        if (isGameClear(balls)) {
            answer = Math.min(answer, depth);
            return;
        }
        for (int dir = 0; dir < 4; ++dir) {
            Balls res = tilt(balls, dir);
            dfs(res, depth + 1);
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];

        Pair blue = null, red = null;

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'B') {
                    blue = new Pair(i, j);
                    graph[i][j] = '.';
                }
                else if (graph[i][j] == 'R') {
                    red = new Pair(i, j);
                    graph[i][j] = '.';
                }
            }
        }

        dfs(new Balls(blue, red),  0);
        if (answer == INF)
            answer = -1;
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new BeadEscape2Dfs().solution();
    }
}
