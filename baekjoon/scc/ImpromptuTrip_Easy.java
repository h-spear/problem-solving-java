// https://www.acmicpc.net/problem/26146

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class ImpromptuTrip_Easy {

    private static List<List<Integer>> graph;
    private static int id;
    private static int sccCount;
    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static int[] visited = new int[200020];
    private static boolean[] finished = new boolean[200020];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(v).add(w);
        }

        for (int i = 1; i <= N; ++i) {
            if (!finished[i])
                dfs(i);
        }

        System.out.println(sccCount == 1 ? "Yes" : "No");
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
            while (!stack.isEmpty()) {
                int node = stack.pop();
                finished[node] = true;
                if (node == x)
                    break;
            }
            ++sccCount;
        }
        return parent;
    }
}
