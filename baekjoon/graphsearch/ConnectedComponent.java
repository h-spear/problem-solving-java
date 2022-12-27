// https://www.acmicpc.net/problem/11724

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class ConnectedComponent {

    private int bfs(int i, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next: graph.get(now)) {
                if (visited[next])
                    continue;
                visited[next] = true;
                queue.add(next);
            }
        }
        return 1;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int answer = 0;
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            if (visited[i])
                continue;
            answer += bfs(i, graph, visited);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new ConnectedComponent().solution();
    }
}
