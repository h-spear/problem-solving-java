// 4112

package swea.difficulty5;

import java.io.*;
import java.util.*;

class ExploreStrangePyramid {

    private static final int[] dx = {1, -1, 0, 0, 1, -1};
    private static final int[] dy = {0, 0, 1, -1, 1, -1};
    private static List<List<Integer>> graph;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        initialize();

        int T = Integer.parseInt(br.readLine());

        int a, b;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write("#" + test_case + " " + bfs(a, b));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int a, int b) {
        int[] visited = new int[10001];
        Queue<Integer> queue = new LinkedList<>();
        visited[a] = 1;
        queue.add(a);

        while (queue.size() >= 1) {
            int x = queue.poll();

            if (x == b)
                break;

            for (int y: graph.get(x)) {
                if (visited[y] != 0)
                    continue;
                queue.add(y);
                visited[y] = visited[x] + 1;
            }
        }
        return visited[b] - 1;
    }

    private static void initialize() {
        int[][] nums = new int[150][150];
        int num = 0;
        for (int i = 0; i < 150; ++i) {
            for (int j = 0; j <= i; ++j) {
                nums[i][j] = ++num;
            }
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= 10000; ++i) {
            graph.add(new ArrayList<>());
        }

        int nx, ny;
        for (int i = 0; i < 150; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (nums[i][j] > 10000) {
                    break;
                }
                for (int k = 0; k < 6; ++k) {
                    nx = i + dx[k];
                    ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= 150 || ny >= 150)
                        continue;
                    if (nums[nx][ny] == 0 || nums[nx][ny] > 10000)
                        continue;
                    graph.get(nums[i][j]).add(nums[nx][ny]);
                }
            }
        }
    }
}
