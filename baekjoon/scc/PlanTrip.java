// https://www.acmicpc.net/problem/2152

package baekjoon.scc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PlanTrip {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static int id;
    private static int[] visited;
    private static boolean[] finished;
    private static List<List<Integer>> scc;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        scc = new ArrayList<>();
        visited = new int[N + 1];
        finished = new boolean[N + 1];
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
        }

        for (int i = 1; i <= N; ++i) {
            if (!finished[i])
                dfs(i);
        }

        int countOfComponents = scc.size();
        int[] sccId = new int[N + 1];
        int[] sccSize = new int[countOfComponents];
        for (int i = 0; i < countOfComponents; ++i) {
            List<Integer> component = scc.get(i);
            for (int node: component) {
                sccId[node] = i;
                ++sccSize[i];
            }
        }

        List<List<Integer>> graph2 = new ArrayList<>(countOfComponents);
        for (int i = 0; i < countOfComponents; ++i)
            graph2.add(new ArrayList<>());

        for (int i = 1; i <= N; ++i) {
            for (int j: graph.get(i)) {
                if (sccId[i] != sccId[j])
                    graph2.get(sccId[i]).add(sccId[j]);
            }
        }

        S = sccId[S];
        T = sccId[T];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(S);
        int[] dp = new int[countOfComponents];
        dp[S] = sccSize[S];
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next: graph2.get(now)) {
                int count = dp[now] + sccSize[next];
                if (dp[next] < count) {
                    dp[next] = count;
                    queue.add(next);
                }
            }
        }
        System.out.println(dp[T]);
        br.close();
    }

    private static int dfs(int x) {
        int parent = visited[x] = ++id;
        stack.push(x);
        for (int next: graph.get(x)) {
            if (visited[next] == 0)
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
