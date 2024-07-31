// https://www.acmicpc.net/problem/3977

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class SoccerTactics {

    private static final int MAX_SIZE = 100010;
    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static final int[] visited = new int[MAX_SIZE];
    private static final boolean[] finished = new boolean[MAX_SIZE];
    private static List<List<Integer>> scc = new ArrayList<>();
    private static List<List<Integer>> graph;
    private static int id;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Arrays.fill(visited, -1);
        int[] sccId = new int[MAX_SIZE];
        int[] sccInDegree = new int[MAX_SIZE];

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>(N);
            for (int i = 0; i < N; ++i)
                graph.add(new ArrayList<>());

            for (int i = 0; i < M; ++i) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                graph.get(A).add(B);
            }

            for (int i = 0; i < N; ++i) {
                if (!finished[i])
                    tarjan(i);
            }

            int countOfComponents = scc.size();
            for (int i = 0; i < countOfComponents; ++i) {
                List<Integer> component = scc.get(i);
                for (int node: component)
                    sccId[node] = i;
            }

            for (int i = 0; i < N; ++i) {
                for (int j: graph.get(i)) {
                    if (sccId[i] != sccId[j])
                        ++sccInDegree[sccId[j]];
                }
            }

            boolean confused = false;
            int startId = -1;
            for (int i = 0; i < countOfComponents; ++i) {
                if (sccInDegree[i] == 0) {
                    if (startId == -1)
                        startId = i;
                    else {
                        confused = true;
                        break;
                    }
                }
            }
            if (confused)
                sb.append("Confused\n");
            else {
                List<Integer> component = scc.get(startId);
                component.sort((o1, o2) -> Integer.compare(o1, o2));
                for (int x: component)
                    sb.append(x).append("\n");
            }
            if (T > 0) sb.append("\n");

            id = 0;
            Arrays.fill(sccInDegree, 0, N, 0);
            Arrays.fill(finished, 0, N, false);
            Arrays.fill(visited, 0, N, -1);
            scc = new ArrayList<>();
            if (T > 0) br.readLine();
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int tarjan(int x) {
        int parent = visited[x] = ++id;
        stack.push(x);
        for (int next: graph.get(x)) {
            if (visited[next] == -1)
                parent = Math.min(parent, tarjan(next));
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
