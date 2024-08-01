// https://www.acmicpc.net/problem/11281

package baekjoon.scc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TwoSatisfiabilityProblem4 {

    private static final Deque<Integer> stack = new ArrayDeque<>();
    private static int[] visited;
    private static boolean[] finished;
    private static List<List<Integer>> scc;
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
        scc = new ArrayList<>();
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

        StringBuilder sb = new StringBuilder();
        sb.append(possible ? 1 : 0);
        if (possible) {
            sb.append("\n");
            int[] cnf = getCNF(N);
            for (int i = 1; i <= N; ++i)
                sb.append(cnf[i]).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int[] getCNF(int n) {
        int[] cnf = new int[n + 1];
        Arrays.fill(cnf, -1);
        int countOfComponents = scc.size();
        for (int i = countOfComponents - 1; i >= 0; --i) {
            for (int node: scc.get(i)) {
                if (cnf[node >> 1] == -1)
                    cnf[node >> 1] = (node & 1) == 0 ? 0 : 1;
            }
        }
        return cnf;
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
            int num = scc.size();
            while (!stack.isEmpty()) {
                int node = stack.pop();
                finished[node] = true;
                component.add(node);
                sccId[node] = num;
                if (node == x)
                    break;
            }
            scc.add(component);
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
