// https://www.acmicpc.net/problem/6543

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class GraphSink {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static final int[] visited = new int[5050];
    private static final boolean[] finished = new boolean[5050];
    private static final int[] sccId = new int[5050];
    private static int id;
    private static List<List<Integer>> scc;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; ++i)
                graph.add(new ArrayList<>());
            scc = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; ++i) {
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph.get(v).add(w);
            }

            for (int i = 1; i <= n; ++i) {
                if (!finished[i])
                    dfs(i);
            }

            int countOfComponents = scc.size();
            int[] sccOutDegree = new int[countOfComponents];
            for (int i = 1; i <= n; ++i) {
                for (int j: graph.get(i)) {
                    if (sccId[i] != sccId[j])
                        ++sccOutDegree[sccId[i]];
                }
            }

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < countOfComponents; ++i) {
                if (sccOutDegree[i] == 0) {
                    result.addAll(scc.get(i));
                }
            }
            result.sort((o1, o2) -> Integer.compare(o1, o2));

            for (int x: result)
                sb.append(x).append(" ");
            sb.append("\n");

            id = 0;
            Arrays.fill(visited, 0, n + 1, 0);
            Arrays.fill(finished, 0, n + 1, false);
            Arrays.fill(sccId, 0, n + 1, 0);
        }
        System.out.println(sb.toString());
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
            int s = scc.size();
            List<Integer> component = new ArrayList<>();
            while (!stack.isEmpty()) {
                int node = stack.pop();
                component.add(node);
                finished[node] = true;
                sccId[node] = s;
                if (node == x)
                    break;
            }
            scc.add(component);
        }
        return parent;
    }
}
