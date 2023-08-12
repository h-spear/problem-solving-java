// https://www.acmicpc.net/problem/1976

package baekjoon.disjointset;

import java.io.*;
import java.util.*;

public class GoTravel {

    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 0; i < N; ++i)
            parent[i] = i;

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; ++j) {
                if (Integer.parseInt(st.nextToken()) == 0)
                    continue;
                union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        boolean flag = true;
        for (int i = 1; i < M; ++i) {
            if (find(first) != find(Integer.parseInt(st.nextToken()))) {
                flag = false;
                break;
            }
        }

        bw.write(flag ? "YES" : "NO");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}
