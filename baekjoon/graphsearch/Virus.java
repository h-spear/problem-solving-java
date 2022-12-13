// https://www.acmicpc.net/problem/2606

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Virus {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
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

        int[] visited = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = 1;
        while (queue.size() != 0) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                if (visited[next] != 0)
                    continue;
                visited[next] = 1;
                queue.add(next);
            }
        }

        int answer = Arrays.stream(visited).sum() - 1;
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Virus().solution();
    }
}
