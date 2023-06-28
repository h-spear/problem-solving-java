// 4193

package swea.difficulty4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SwimmingCompetitonFinal {

    static class Pair {
        int x, y, t;

        public Pair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N;
    private static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int sx, sy, tx, ty;

        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(br.readLine());
            graph = new int[N][N];
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; ++j) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            tx = Integer.parseInt(st.nextToken());
            ty = Integer.parseInt(st.nextToken());

            if (isReachable(sx, sy, tx, ty)) {
                bw.write("#" + tc + " " + bfs(sx, sy, tx, ty));
            } else {
                bw.write("#" + tc + " -1");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isReachable(int sx, int sy, int tx, int ty) {
        int nx, ny;
        boolean[][] visited = new boolean[N][N];

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(sx, sy, 0));
        visited[sx][sy] = true;
        while (queue.size() > 0) {
            Pair p = queue.poll();

            if (p.x == tx && p.y == ty) {
                return true;
            }

            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (graph[nx][ny] == 1)
                    continue;
                queue.add(new Pair(nx, ny, p.t + 1));
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    private static int bfs(int sx, int sy, int tx, int ty) {
        int nx, ny;
        boolean[][] visited = new boolean[N][N];

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(sx, sy, 0));
        visited[sx][sy] = true;
        while (queue.size() > 0) {
            Pair p = queue.poll();

            if (p.x == tx && p.y == ty) {
                return p.t;
            }

            queue.add(new Pair(p.x, p.y, p.t + 1));

            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (graph[nx][ny] == 1)
                    continue;
                if (graph[nx][ny] == 2 && p.t % 3 != 2)
                    continue;
                queue.add(new Pair(nx, ny, p.t + 1));
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}