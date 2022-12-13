// https://www.acmicpc.net/problem/1260

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class DfsAndBfs {
    private static boolean visited[];
    private static List<List<Integer>> graph = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    private void dfs(int x) {
        visited[x] = true;
        path.add(x);
        for (int next : graph.get(x)) {
            if (visited[next])
                continue;
            dfs(next);
        }
    }

    private void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        visited[x] = true;
        while (queue.size() != 0) {
            int now = queue.poll();
            path.add(now);

            for (int next : graph.get(now)) {
                if (visited[next])
                    continue;
                visited[next] = true;
                queue.add(next);
            }
        }
    }
    
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; ++i)
            graph.add(new LinkedList<>());

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 0; i <= n; ++i) {
            graph.get(i).sort(Comparator.naturalOrder());
        }

        visited = new boolean[n + 1];
        path = new ArrayList<>();
        dfs(v);
        for (int node : path)
            bw.write(node + " ");
        bw.newLine();

        visited = new boolean[n + 1];
        path = new ArrayList<>();
        bfs(v);
        for (int node : path)
            bw.write(node + " ");
        bw.newLine();

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new DfsAndBfs().solution();
    }
}
