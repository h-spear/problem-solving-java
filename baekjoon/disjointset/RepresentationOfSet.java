// https://www.acmicpc.net/problem/1717

package baekjoon.disjointset;

import java.io.*;
import java.util.*;

public class RepresentationOfSet {

    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            parent[i] = i;

        int c, a, b;
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (c == 0) {
                 union(parent, a, b);
            } else {
                if (find(parent, a) == find(parent, b))
                    bw.write("YES");
                else
                    bw.write("NO");
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
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
}
