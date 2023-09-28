// https://www.acmicpc.net/problem/13925

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery13 {

    private static class Node {
        long a, b;

        Node() {
            invalidate();
        }

        void update(long a, long b) {
            this.a = (this.a * a) % MOD;
            this.b = (this.b * a) % MOD;
            this.b = (this.b + b) % MOD;
        }

        void invalidate() {
            this.a = 1;
            this.b = 0;
        }
    }

    private static final int MOD = 1_000_000_007;
    private static int N, S;
    private static long[] tree;
    private static Node[] lazy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = 1;
        while (S < N)
            S <<= 1;
        tree = new long[S << 1];
        lazy = new Node[S << 1];

        // initialize
        st = new StringTokenizer(br.readLine());
        for (int i = 0, S2 = S << 1; i < S2; ++i) {
            lazy[i] = new Node();
        }
        for (int i = 0; i < N; ++i) {
            tree[S + i] = Integer.parseInt(st.nextToken());
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = (tree[i << 1] + tree[(i << 1) | 1]) % MOD;
        }

        // query, update
        int M = Integer.parseInt(br.readLine());
        int c, x, y, v;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            switch (c) {
                case 1:
                    v = Integer.parseInt(st.nextToken());
                    update(1, 0, S - 1, x, y, 1, v);
                    break;
                case 2:
                    v = Integer.parseInt(st.nextToken());
                    update(1, 0, S - 1, x, y, v, 0);
                    break;
                case 3:
                    v = Integer.parseInt(st.nextToken());
                    update(1, 0, S - 1, x, y, 0, v);
                    break;
                case 4:
                    bw.write(query(1, 0, S - 1, x, y) + "\n");
                    break;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static long query(int node, int left, int right, int queryLeft, int queryRight) {
        propagate(node, left, right);
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return (query(node << 1, left, mid, queryLeft, queryRight) +
                query((node << 1) | 1, mid + 1, right, queryLeft, queryRight)) % MOD;
    }

    private static void update(int node, int left, int right, int updateLeft, int updateRight, int a, int b) {
        propagate(node, left, right);
        if (updateRight < left || right < updateLeft) {
            return;
        }
        if (updateLeft <= left && right <= updateRight) {
            lazy[node].update(a, b);
            propagate(node, left, right);
            return;
        }
        int mid = (left + right) >> 1;
        update(node << 1, left, mid, updateLeft, updateRight, a, b);
        update((node << 1) | 1, mid + 1, right, updateLeft, updateRight, a, b);
        tree[node] = (tree[node << 1] + tree[(node << 1) | 1]) % MOD;
    }

    private static void propagate(int node, int left, int right) {
        long a = lazy[node].a;
        long b = lazy[node].b;
        tree[node] = (a * tree[node] + b * (right - left + 1)) % MOD;
        if (left != right) {
            lazy[node << 1].update(a, b);
            lazy[(node << 1) | 1].update(a, b);
        }
        lazy[node].invalidate();
    }
}
