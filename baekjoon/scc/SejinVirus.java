// https://www.acmicpc.net/problem/15783

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class SejinVirus {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static final int[] visited = new int[100010];
    private static final boolean[] finished = new boolean[100010];
    private static final int[] sccId = new int[100010];
    private static final List<List<Integer>> scc = new ArrayList<>();
    private static int id;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(N);
        for (int i = 0; i < N; ++i)
            graph.add(new ArrayList<>());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
        }

        for (int i = 0; i < N; ++i) {
            if (!finished[i])
                dfs(i);
        }

        int countOfComponents = scc.size();
        int[] sccInDegree = new int[countOfComponents];
        for (int i = 0; i < N; ++i) {
            for (int j: graph.get(i)) {
                if (sccId[i] != sccId[j]) {
                    ++sccInDegree[sccId[j]];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < countOfComponents; ++i) {
            if (sccInDegree[i] == 0)
                ++answer;
        }
        System.out.println(answer);
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
                finished[node] = true;
                component.add(node);
                sccId[node] = scc.size();
                if (node == x)
                    break;
            }
            scc.add(component);
        }
        return parent;
    }
}
