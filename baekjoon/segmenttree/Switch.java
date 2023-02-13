// https://www.acmicpc.net/problem/1395

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Switch {

    private static int N, M, S;
    private static int[] sw;
    private static int[] tree;
    private static int[] lazy;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int o, s, t;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sw = new int[N];
        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];
        lazy = new int[S * 2];

        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            o = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            if (o == 0) {
                update(1, 0, S - 1, s - 1, t - 1);
            } else {
                bw.write(query(1, 0, S - 1, s - 1, t - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        updateLazy(node, left, right);
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, queryLeft, queryRight) +
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private void update(int node, int left, int right, int updateLeft, int updateRight) {
        updateLazy(node, left, right);
        if (updateRight < left || right < updateLeft) {
            return;
        }
        if (updateLeft <= left && right <= updateRight) {
            tree[node] = (right - left + 1 - tree[node]);
            if (left != right) {
                lazy[node * 2] = 1 - lazy[node * 2];
                lazy[node * 2 + 1] = 1 - lazy[node * 2 + 1];
            }
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, updateLeft, updateRight);
            update(node * 2 + 1, mid + 1, right, updateLeft, updateRight);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private void updateLazy(int node, int left, int right) {
        if (lazy[node] != 0) {
            tree[node] = (right - left + 1 - tree[node]);
            if (left != right) {
                lazy[node * 2] = 1 - lazy[node * 2];
                lazy[node * 2 + 1] = 1 - lazy[node * 2 + 1];
            }
            lazy[node] = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        new Switch().solution();
    }
}
