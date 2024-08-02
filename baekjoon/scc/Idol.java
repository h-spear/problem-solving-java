// https://www.acmicpc.net/problem/3648

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class Idol {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static final int[] visited = new int[2020];
    private static final boolean[] finished = new boolean[2020];
    private static final int[] sccId = new int[2020];
    private static int id, sccCount;
    private static int n;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                int countOfNodes = input(br);
                sb.append(solve(countOfNodes) ? "yes" : "no")
                        .append("\n");
                clear(countOfNodes);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static boolean solve(int countOfNodes) {
        // tarjan
        for (int i = 2; i < countOfNodes; ++i) {
            if (!finished[i])
                dfs(i);
        }

        for (int i = 1; i <= n; ++i) {
            if (sccId[trueX(i)] == sccId[falseX(i)])
                return false;
        }
        return true;
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
            ++sccCount;
            while (!stack.isEmpty()) {
                int node = stack.pop();
                sccId[node] = sccCount;
                finished[node] = true;
                if (node == x)
                    break;
            }
        }
        return parent;
    }

    private static void clear(int countOfNodes) {
        id = 0;
        sccCount = 0;
        Arrays.fill(visited, 0, countOfNodes + 1, 0);
        Arrays.fill(sccId, 0, countOfNodes + 1, 0);
        Arrays.fill(finished, 0, countOfNodes + 1, false);
    }

    private static int input(BufferedReader br) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int countOfNodes = (n << 1) + 2;

        graph = new ArrayList<>(countOfNodes);
        for (int i = 0; i <= countOfNodes; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            a = a > 0 ? trueX(a) : falseX(-a);
            b = b > 0 ? trueX(b) : falseX(-b);
            graph.get(notX(a)).add(b);
            graph.get(notX(b)).add(a);
        }
        graph.get(falseX(1)).add(trueX(1));
        return countOfNodes;
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