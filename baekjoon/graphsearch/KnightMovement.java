// https://www.acmicpc.net/problem/7562

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class KnightMovement {

    private static final int[] dx = {-1, -1, -2, -2, 1, 1, 2, 2};
    private static final int[] dy = {-2, 2, -1, 1, -2, 2, -1, 1};

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; ++tc) {

            int n = Integer.parseInt(br.readLine());
            int x, y, tx, ty, nx, ny;
            Queue<Pair> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            tx = Integer.parseInt(st.nextToken());
            ty = Integer.parseInt(st.nextToken());

            int[][] visited = new int[n][n];
            queue.add(new Pair(x, y));
            visited[x][y] = 1;
            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                x = pair.x;
                y = pair.y;

                if (x == tx && y == ty)
                    break;

                for (int i = 0; i < 8; ++i) {
                    nx = x + dx[i];
                    ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                        continue;
                    if (visited[nx][ny] != 0)
                        continue;
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = visited[x][y] + 1;
                }
            }
            System.out.println(visited[tx][ty] - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        new KnightMovement().solution();
    }
}
