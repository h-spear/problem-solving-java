// https://www.acmicpc.net/problem/3055

package baekjoon.graphsearch;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Escape {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    static class Pair {
        int x, y;
        boolean isWater;

        public Pair(int x, int y, boolean isWater) {
            this.x = x;
            this.y = y;
            this.isWater = isWater;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("src/day01/p3055/input.txt"));

        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        char[][] graph = new char[r][c];
        int[][] visited = new int[r][c];
        for (int i = 0; i < r; ++i) {
            String input = sc.next();
            for (int j = 0; j < c; ++j) {
                graph[i][j] = input.charAt(j);
            }
        }

        // 초기화 - 물 먼저 큐에 입력
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (graph[i][j] == '*') {
                    queue.add(new Pair(i, j, true));
                }
            }
        }
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (graph[i][j] == 'S') {
                    queue.add(new Pair(i, j, false));
                    graph[i][j] = '.';
                    visited[i][j] = 1;
                }
            }
        }

        // bfs
        int x, y, nx, ny;
        boolean isWater;
        while (!queue.isEmpty()) {
            // 1. 큐에서 가져옴
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;
            isWater = pair.isWater;

            // 2. 목적지인가?
            if (!isWater && graph[x][y] == 'D') {
                System.out.println(visited[x][y] - 1);
                return;
            }

            // 3. 연결된 곳을 순회
            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                // 4. 갈 수 있는가?
                // 맵 밖으로 나갈 수 없음
                if (nx < 0 || ny < 0 || nx >= r || ny >= c)
                    continue;
                // 물이 있는 곳으로 이동할 수 없음
                if (graph[nx][ny] == '*')
                    continue;
                // 돌이 있는 곳으로 이동할 수 없음
                if (graph[nx][ny] == 'X')
                    continue;
                if (isWater) {
                    // 물은 비버 굴로 이동할 수 없음
                    if (graph[nx][ny] == 'D') {
                        continue;
                    }
                    // 5. 체크인 (물)
                    graph[nx][ny] = '*';
                } else {
                    // 고슴도치는 방문한 곳을 재방문할 수 없음
                    if (visited[nx][ny] != 0) {
                        continue;
                    }
                    // 5. 체크인 (고슴도치)
                    visited[nx][ny] = visited[x][y] + 1;
                }

                // 6. 큐에 넣음
                queue.add(new Pair(nx, ny, isWater));
            }
        }
        System.out.println("KAKTUS");
    }
}
