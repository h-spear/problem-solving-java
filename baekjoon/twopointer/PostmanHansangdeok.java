// https://www.acmicpc.net/problem/2842
// bfs + two pointer
// ****

package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class PostmanHansangdeok {

    private static int N, houseCount;
    private static char[][] graph;
    private static int[][] height;
    private static Pair P;
    private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        houseCount = 0;

        graph = new char[N][N];
        height = new int[N][N];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < N; ++j) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'P') {
                    P = new Pair(i, j);
                } else if (graph[i][j] == 'K') {
                    houseCount++;
                }
            }
        }
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                height[i][j] = Integer.parseInt(st.nextToken());
                set.add(height[i][j]);
            }
        }

        // two pointer
        List<Integer> heightList = new ArrayList<>(set);
        Collections.sort(heightList);

        int low = 0;
        int high = 0;

        int answer = Integer.MAX_VALUE;
        while (low <= high && high < heightList.size()) {
            if (bfs(heightList.get(low), heightList.get(high))) {
                answer = Math.min(answer, heightList.get(high) - heightList.get(low));
                low++;
            } else {
                high++;
            }
        }
        System.out.println(answer);
        br.close();
    }

    private boolean bfs(int min, int max) {
        if (height[P.x][P.y] < min || height[P.x][P.y] > max) {
            return false;
        }

        boolean[][] visited = new boolean[N][N];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(P);
        visited[P.x][P.y] = true;

        int x, y, nx, ny, cnt = 0;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;

            if (graph[x][y] == 'K') {
                cnt++;
            }

            for (int i = 0; i < 8; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (height[nx][ny] < min || height[nx][ny] > max)
                    continue;

                visited[nx][ny] = true;
                queue.add(new Pair(nx, ny));
            }
        }
        if (cnt == houseCount) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        new PostmanHansangdeok().solution();
    }
}
