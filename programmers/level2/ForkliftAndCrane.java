// https://school.programmers.co.kr/learn/courses/30/lessons/388353#

package programmers.level2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ForkliftAndCrane {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        char[][] graph = new char[n + 2][m + 2];

        fill2D(graph, '\0');
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                graph[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
        n += 2;
        m += 2;

        for (String request: requests) {
            char key = request.charAt(0);
            if (request.length() == 2) {
                removeAll(n, m, graph, key);
            } else {
                removeUsingBfs(n, m, graph, key);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (graph[i][j] != '\0')
                    ++answer;
            }
        }
        return answer;
    }

    private void removeAll(int n, int m, char[][] graph, char key) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (graph[i][j] == key)
                    graph[i][j] = '\0';
            }
        }
    }

    private void removeUsingBfs(int n, int m, char[][] graph, char key) {
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        queue.add(new Pair(0, 0));
        visited[0][0] = true;

        while (queue.size() > 0) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (graph[nx][ny] == '\0') {
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = true;
                } else if (graph[nx][ny] == key) {
                    visited[nx][ny] = true;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (visited[i][j])
                    graph[i][j] = '\0';
            }
        }
    }

    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void fill2D(char[][] array, char value) {
        for (char[] row: array) {
            Arrays.fill(row, value);
        }
    }
}
