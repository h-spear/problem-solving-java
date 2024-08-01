// https://www.acmicpc.net/problem/1506

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class PoliceOffice {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static int[] visited;
    private static boolean[] finished;
    private static int id;
    private static int N;
    private static List<List<Integer>> scc;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] cost = new int[N];
        for (int i = 0; i < N; ++i) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>(N);
        for (int i = 0; i < N; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < N; ++i) {
            String input = br.readLine();
            for (int j = 0; j < N; ++j) {
                if (input.charAt(j) == '1') {
                    graph.get(i).add(j);
                }
            }
        }

        tarjan();
        System.out.println(solve(cost));
        br.close();
    }

    private static int solve(int[] cost) {
        int total = 0;
        for (List<Integer> component: scc) {
            int min = 1234567;
            for (int node: component) {
                min = Math.min(min, cost[node]);
            }
            total += min;
        }
        return total;
    }

    private static void tarjan() {
        id = 0;
        scc = new ArrayList<>();
        visited = new int[N];
        finished = new boolean[N];
        Arrays.fill(visited, -1);

        for (int i = 0; i < N; ++i) {
            if (!finished[i])
                dfs(i);
        }
    }

    private static int dfs(int x) {
        int parent = visited[x] = ++id;
        stack.push(x);
        for (int next: graph.get(x)) {
            if (visited[next] == -1)
                parent = Math.min(parent, dfs(next));
            else if (!finished[next])
                parent = Math.min(parent, visited[next]);
        }

        if (parent == visited[x]) {
            List<Integer> component = new ArrayList<>();
            while (!stack.isEmpty()) {
                int node = stack.pop();
                component.add(node);
                finished[node] = true;
                if (node == x)
                    break;
            }
            scc.add(component);
        }
        return parent;
    }
}
