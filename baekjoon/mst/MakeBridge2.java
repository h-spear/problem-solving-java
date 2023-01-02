// https://www.acmicpc.net/problem/17472

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class MakeBridge2 {
    private static int n;
    private static int m;
    private static int[][] graph;
    private static int[][] group;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static List<Edge> edges = new ArrayList<>();
    private static Map<Pair, Integer> lengthMap = new HashMap<>();

    class Edge {
        int x;
        int y;
        int cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    private void bfs(int x, int y, int groupId) {
        int nx, ny;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        group[x][y] = groupId;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;

            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                if (graph[nx][ny] == 0)
                    continue;
                if (group[nx][ny] != 0)
                    continue;

                queue.add(new Pair(nx, ny));
                group[nx][ny] = groupId;
            }
        }
    }

    private void generateEdge() {
        int nx, ny;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (group[i][j] == 0)
                    continue;

                int groupId = group[i][j];
                for (int k = 0; k < 4; ++k) {
                    nx = i + dx[k];
                    ny = j + dy[k];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                        continue;
                    if (group[nx][ny] != 0)
                        continue;

                    boolean flag = true;
                    int length = 0;
                    while (group[nx][ny] == 0) {
                        nx += dx[k];
                        ny += dy[k];
                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                            flag = false;
                            break;
                        }
                        ++length;
                    }
                    if (flag && length >= 2) {
                        int value = Math.min(length, lengthMap.getOrDefault(new Pair(groupId, group[nx][ny]), Integer.MAX_VALUE));
                        lengthMap.put(new Pair(groupId, group[nx][ny]), value);
                    }
                }
            }
        }
        for (Pair pair: lengthMap.keySet()) {
            edges.add(new Edge(pair.x, pair.y, lengthMap.get(pair)));
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        group = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // grouping
        int groupCount = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (graph[i][j] == 0)
                    continue;
                if (group[i][j] != 0)
                    continue;
                bfs(i, j, ++groupCount);
            }
        }

        generateEdge();

        // kruskal
        int[] parent = new int[groupCount + 1];
        for (int i = 0; i <= groupCount; ++i) {
            parent[i] = i;
        }

        edges.sort((a, b) -> (a.cost - b.cost));

        int answer = 0;
        for (Edge edge: edges) {
            if (find(parent, edge.x) == find(parent, edge.y))
                continue;

            union(parent, edge.x, edge.y);
            answer += edge.cost;
        }

        boolean possible = true;
        for (int i = 1; i <= groupCount; ++i) {
            if (find(parent, i) != 1)
                possible = false;
        }

        if (!possible)
            answer = -1;

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new MakeBridge2().solution();
    }
}
