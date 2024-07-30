// https://www.acmicpc.net/problem/4196

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class Domino {

    private static int id;
    private static final int[] visited = new int[100010];
    private static final boolean[] finished = new boolean[100010];
    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static List<List<Integer>> scc;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] sccId = new int[100010];
        int[] sccInDegree = new int[100010];

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            scc = new ArrayList<>();
            graph = new ArrayList<>(N + 1);
            for (int i = 0; i <= N; ++i)
                graph.add(new ArrayList<>());

            for (int i = 0; i < M; ++i) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph.get(x).add(y);
            }


            for (int i = 1; i <= N; ++i) {
                if (!finished[i])
                    tarjan(i);
            }

            int countOfComponents = scc.size();
            for (int i = 0; i < countOfComponents; ++i) {
                List<Integer> component = scc.get(i);
                for (int node: component) {
                    sccId[node] = i;
                }
            }

            for (int i = 1; i <= N; ++i) {
                for (int j: graph.get(i)) {
                    if (sccId[i] != sccId[j])
                        ++sccInDegree[sccId[j]];
                }
            }

            int answer = 0;
            for (int i = 0; i < countOfComponents; ++i) {
                if (sccInDegree[i] == 0)
                    ++answer;
            }
            sb.append(Math.max(answer, 1)).append("\n");

            id = 0;
            Arrays.fill(sccId, 0, N + 1, 0);
            Arrays.fill(sccInDegree, 0, N + 1, 0);
            Arrays.fill(visited, 0, N + 1, 0);
            Arrays.fill(finished, 0, N + 1, false);
            stack.clear();
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int tarjan(int x) {
        int parent = visited[x] = ++id;
        stack.push(x);

        for (int next: graph.get(x)) {
            if (visited[next] == 0) {
                parent = Math.min(parent, tarjan(next));
            } else if (!finished[next]) {
                parent = Math.min(parent, visited[next]);
            }
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
