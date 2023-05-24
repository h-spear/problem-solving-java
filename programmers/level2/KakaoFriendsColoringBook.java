// https://school.programmers.co.kr/learn/courses/30/lessons/1829

package programmers.level2;

import java.util.*;

public class KakaoFriendsColoringBook {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int[][] picture;
    private static boolean[][] visited;
    private static int m, n;

    public int[] solution(int m, int n, int[][] picture) {
        this.m = m;
        this.n = n;
        this.picture = picture;
        this.visited = new boolean[m][n];

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j));
                ++numberOfArea;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int x, int y) {
        int nx, ny, area = 0;
        int color = picture[x][y];
        visited[x][y] = true;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));

        while (queue.size() > 0) {
            ++area;
            Pair p = queue.poll();

            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                if (picture[nx][ny] != color) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                queue.add(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return area;
    }
}

class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}