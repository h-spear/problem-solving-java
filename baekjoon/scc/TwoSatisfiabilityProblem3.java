// https://www.acmicpc.net/problem/11280

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class TwoSatisfiabilityProblem3 {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static int[] visited;
    private static boolean[] finished;
    private static int sccCount;
    private static int[] sccId;
    private static int id;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int countOfNodes = (N << 1) + 2;
        visited = new int[countOfNodes];
        finished = new boolean[countOfNodes];
        sccId = new int[countOfNodes];
        graph = new ArrayList<>(countOfNodes);
        for (int i = 0; i < countOfNodes; ++i)
            graph.add(new ArrayList<>());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            i = i > 0 ? trueX(i) : falseX(-i);
            j = j > 0 ? trueX(j) : falseX(-j);
            graph.get(notX(i)).add(j);
            graph.get(notX(j)).add(i);
        }

        for (int i = 2; i < countOfNodes; ++i) {
            if (!finished[i])
                dfs(i);
        }

        boolean possible = true;
        for (int i = 1; i <= N; ++i) {
            if (sccId[trueX(i)] == sccId[falseX(i)]) {
                possible = false;
                break;
            }
        }
        System.out.println(possible ? 1 : 0);
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
