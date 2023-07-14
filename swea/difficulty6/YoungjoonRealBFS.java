// 1855

package swea.difficulty6;

import java.io.*;
import java.util.*;

public class YoungjoonRealBFS {

    private static int N;
    private static int[] depth;
    private static int[][] parent;
    private static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(br.readLine());
            graph = new ArrayList<>();
            depth = new int[N+1];
            parent = new int[18][N+1];
            for (int i = 0; i <= N; ++i) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 2; i <= N; ++i) {
                int p = Integer.parseInt(st.nextToken());
                graph.get(i).add(p);
                graph.get(p).add(i);
            }

            // sorting
            for (int i = 1; i <= N; ++i) {
                Collections.sort(graph.get(i));
            }

            getDepthBfs(1);
            fillParent();
            List<Integer> bfsResult = bfs(1);
            long distance = 0;
            for (int i = 0; i < N-1; ++i) {
                distance += lca(bfsResult.get(i), bfsResult.get(i+1));
            }
            bw.write("#" + tc + " " + distance + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static List<Integer> bfs(int x) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(x);
        visited[x] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);
            for (int next: graph.get(curr)) {
                if (visited[next])
                    continue;
                queue.add(next);
                visited[next] = true;
            }
        }
        return result;
    }

    // depth를 dfs로 구하면 runtime error 발생(스택 메모리 부족)
    private static void getDepth(int curr, int dep) {
        depth[curr] = dep;
        for (int next: graph.get(curr)) {
            if (depth[next] == 0) {
                parent[0][next] = curr;
                getDepth(next, dep + 1);
            }
        }
    }

    private static void getDepthBfs(int root) {
        depth[root] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next: graph.get(curr)) {
                if (depth[next] != 0)
                    continue;
                parent[0][next] = curr;
                queue.add(next);
                depth[next] = depth[curr] + 1;
            }
        }
    }

    private static void fillParent() {
        for (int i = 1; i <= 17; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    private static int lca(int a, int b) {
        int res = 0;
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int j = 17; j >= 0; --j) {
            int pow = (int)Math.pow(2, j);
            if (depth[a] - pow >= depth[b]) {
                a = parent[j][a];
                res += pow;
            }

            if (depth[a] == depth[b]) {
                break;
            }
        }

        if (a != b) {
            for (int j = 17; j >= 0; --j) {
                if (parent[j][a] != parent[j][b]) {
                    a = parent[j][a];
                    b = parent[j][b];
                    res += 2 * (int)Math.pow(2, j);
                }
            }
            res += 2;
        }
        return res;
    }
}
