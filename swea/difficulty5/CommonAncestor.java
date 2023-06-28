// 1248

package swea.difficulty5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CommonAncestor {

    private static List<List<Integer>> tree;
    private static int[] depth;
    private static int[] parent;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            int V, E, A, B;
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            depth = new int[V + 1];
            parent = new int[V + 1];
            visited = new boolean[V + 1];
            tree = new ArrayList<>();
            for (int i = 0; i <= V; ++i) {
                tree.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; ++i) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                tree.get(x).add(y);
                parent[y] = x;
            }

            Arrays.fill(visited, false);
            findDepth(1, 0);

            int lca = lca(A, B);
            int size = getTreeSize(lca);
            bw.write("#" + tc + " " + lca + " " + size);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void findDepth(int x, int dep) {
        visited[x] = true;
        depth[x] = dep;
        for (int y: tree.get(x)) {
            if (!visited[y]) {
                findDepth(y, dep + 1);
            }
        }
    }

    private static int getTreeSize(int root) {
        int res = 1;
        for (int child: tree.get(root)) {
            res += getTreeSize(child);
        }
        return res;
    }

    private static int lca(int x, int y) {
        if (depth[x] < depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        while (depth[x] != depth[y]) {
            x = parent[x];
        }

        while (x != y) {
            x = parent[x];
            y = parent[y];
        }
        return x;
    }
}