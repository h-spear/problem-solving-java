// https://www.acmicpc.net/problem/15686

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class ChickenDelivery {

    private static int N, M;
    private static int chickenNum, houseNum;
    private static int answer = Integer.MAX_VALUE;
    private static int[][] graph;
    private static int[][] distanceChickenToHouse;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static List<Integer> selected = new ArrayList<>();

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void bfs(int x, int y, int[] distanceToHouse) {
        int nx, ny;
        int[][] visited = new int[N][N];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = 1;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;

            if (graph[x][y] > 0) {
                distanceToHouse[graph[x][y]] = visited[x][y] - 1;
            }

            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if (visited[nx][ny] != 0)
                    continue;

                queue.add(new Pair(nx, ny));
                visited[nx][ny] = visited[x][y] + 1;
            }
        }
    }

    private int findChickenDistance() {
        int res = 0;
        for (int hNum = 1; hNum <= houseNum; ++hNum) {
            int minDist = Integer.MAX_VALUE;
            for (int cNum: selected) {
                minDist = Math.min(minDist, distanceChickenToHouse[cNum][hNum]);
            }
            res += minDist;
        }
        return res;
    }

    private void dfs(int idx) {
        // 1.
        if (idx != 0) {
            selected.add(idx);
        }

        // 2.
        if (selected.size() == M) {
            answer = Math.min(answer, findChickenDistance());
        } else {
            // 3, 4, 5
            for (int i = idx + 1; i <= chickenNum; ++i) {
                dfs(i);
            }
        }

        // 6.
        if (idx != 0) {
            selected.remove(selected.size() - 1);
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 0 : 빈칸
        // 양수 : 집
        // 음수 : 치킨집
        graph = new int[N][N];
        houseNum = 0;
        chickenNum = 0;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) {
                    graph[i][j] = ++houseNum;
                } else if (input == 2) {
                    ++chickenNum;
                    graph[i][j] = -chickenNum;
                }
            }
        }

        distanceChickenToHouse = new int[chickenNum + 1][houseNum + 1];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (graph[i][j] < 0) {
                    bfs(i, j, distanceChickenToHouse[-graph[i][j]]);
                }
            }
        }

        dfs(0);
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new ChickenDelivery().solution();
    }
}
