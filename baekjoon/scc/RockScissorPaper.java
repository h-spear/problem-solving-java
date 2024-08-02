// https://www.acmicpc.net/problem/2207

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class RockScissorPaper {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static int[] visited;
    private static boolean[] finished;
    private static int[] sccId;
    private static int id;
    private static int sccCount;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int sz = (M << 1) + 5;
        graph = new ArrayList<>(sz);
        for (int i = 0; i < sz; ++i)
            graph.add(new ArrayList<>());
        visited = new int[sz];
        finished = new boolean[sz];
        sccId = new int[sz];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            x = x > 0 ? trueX(x) : falseX(-x);
            y = y > 0 ? trueX(y) : falseX(-y);
            graph.get(notX(x)).add(y);
            graph.get(notX(y)).add(x);
        }

        for (int i = 2; i < sz; ++i) {
            if (!finished[i])
                dfs(i);
        }

        boolean possible = true;
        for (int i = 1; i <= M; ++i) {
            if (sccId[trueX(i)] == sccId[falseX(i)]) {
                possible = false;
                break;
            }
        }
        System.out.println(possible ? "^_^" : "OTL");
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
                sccId[node] = sccCount;
                if (node == x)
                    break;
            }
            ++sccCount;
        }
        return parent;
    }

    private static int trueX(int x) {
        return x << 1;
    }

    private static int falseX(int x) {
        return (x << 1) | 1;
    }

    private static int notX(int x) {
        return x ^ 1;
    }
}
