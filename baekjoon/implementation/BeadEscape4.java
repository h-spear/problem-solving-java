package baekjoon.implementation;// https://www.acmicpc.net/problem/13460

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BeadEscape4 {

    // up, right, down, left
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int n;
    private static int m;
    private static char[][] graph;
    private static Set<Balls> visited = new HashSet<>();

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    class Balls {
        Pair blue;
        Pair red;

        public Balls(Pair blue, Pair red) {
            this.blue = blue;
            this.red = red;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Balls balls = (Balls) o;
            return Objects.equals(blue, balls.blue) && Objects.equals(red, balls.red);
        }

        @Override
        public int hashCode() {
            return Objects.hash(blue, red);
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

    private int bfs(Balls balls) {
        Balls nballs;
        Queue<Balls> queue = new LinkedList<>();
        queue.add(balls);

        int answer = 0;
        visited.add(balls);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; ++i) {
                balls = queue.remove();

                if (isGameClear(balls))
                    return answer;

                for (int dir = 0; dir < 4; ++dir) {
                    nballs = tilt(balls, dir);
                    if (nballs.blue == null)
                        continue;
                    if (visited.contains(nballs))
                        continue;
                    queue.add(nballs);
                    visited.add(nballs);
                }
            }
            ++answer;
        }
        return -1;
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

        System.out.println(bfs(new Balls(blue, red)));
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new BeadEscape4().solution();
    }
}
