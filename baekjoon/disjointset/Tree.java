// https://www.acmicpc.net/problem/13306

package baekjoon.disjointset;

import java.io.*;
import java.util.*;

public class Tree {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] parent = new int[N + 1];
        int[] parentNode = new int[N + 1];
        for (int i = 0; i <= N; ++i) {
            parent[i] = i;
        }

        for (int i = 2; i <= N; ++i) {
            parentNode[i] = Integer.parseInt(br.readLine());
        }

        int queryCount = N + Q - 1;
        Query[] queries = new Query[queryCount];
        for (int i = 0; i < queryCount; ++i) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 0) {
                queries[i] = new Query(type, Integer.parseInt(st.nextToken()));
            } else {
                queries[i] = new Query(type,
                                       Integer.parseInt(st.nextToken()),
                                       Integer.parseInt(st.nextToken()));
            }
        }

        boolean[] result = new boolean[Q];
        for (int i = queryCount - 1, idx = Q - 1; i >= 0; --i) {
            Query query = queries[i];
            if (query.type == 0) {
                int node = query.parameter[0];
                union(parent, node, parentNode[node]);
            } else {
                if (find(parent, query.parameter[0]) == find(parent, query.parameter[1])) {
                    result[idx] = true;
                }
                --idx;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (boolean b: result) {
            sb.append(b ? "YES\n" : "NO\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    private static class Query {
        int type;
        int[] parameter;

        Query(int type, int ...parameter) {
            this.type = type;
            this.parameter = parameter;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "type=" + type +
                    ", parameter=" + Arrays.toString(parameter) +
                    '}';
        }
    }
}
