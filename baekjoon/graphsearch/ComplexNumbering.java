// https://www.acmicpc.net/problem/2667

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class ComplexNumbering {
    private static final int MAX_SIZE = 26;
    private static int[][] graph = new int[MAX_SIZE][MAX_SIZE];
    private static int[][] visited = new int[MAX_SIZE][MAX_SIZE];
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int bfs(int x, int y, int number, int n) {
        int nx, ny;
        int count = 1;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = number;
        while (queue.size() != 0) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;

            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;
                if (visited[nx][ny] != 0)
                    continue;
                if (graph[nx][ny] == 0)
                    continue;
                queue.add(new Pair(nx, ny));
                visited[nx][ny] = number;
                count++;
            }
        }
        return count;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int number = 0;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < n; ++j) {
                if (line.charAt(j) == '1')
                    graph[i][j] = 1;
            }
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (visited[i][j] != 0)
                    continue;
                if (graph[i][j] == 0)
                    continue;
                number++;
                list.add(bfs(i, j, number, n));
            }
        }
        list.sort(Comparator.naturalOrder());
        bw.write("" + list.size());
        bw.newLine();
        for (Integer elem : list) {
            bw.write("" + elem);
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
       new ComplexNumbering().solution();
    }
}
