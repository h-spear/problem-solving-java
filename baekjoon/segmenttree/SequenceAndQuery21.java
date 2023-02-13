// https://www.acmicpc.net/problem/16975

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery21 {

    private static int N, M, S;
    private static int[] A;
    private static long[] tree;
    private static long[] lazy;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        A = new int[N];
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];
        lazy = new long[S * 2];

        init();

        int c, i, j, k, x;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            if (c == 1) {
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());
                k = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, i - 1, j - 1, k);
            } else {
                x = Integer.parseInt(st.nextToken());
                bw.write(query(1, 0, S - 1, x - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = A[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private long query(int node, int left, int right, int target) {
        updateLazy(node, left, right);
        if (target < left || right < target) {
            return 0;
        }
        if (left == right) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return query(node * 2, left, mid, target) +
                    query(node * 2 + 1, mid + 1, right, target);
        }
    }

    private void update(int node, int left, int right, int updateLeft, int updateRight, long diff) {
        updateLazy(node, left, right);
        if (updateRight < left || right < updateLeft) {
            return;
        }
        if (updateLeft <= left && right <= updateRight) {
            tree[node] += diff * (right - left + 1);
            if (left != right) {
                lazy[node * 2] += diff;
                lazy[node * 2 + 1] += diff;
            }
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, updateLeft, updateRight, diff);
            update(node * 2 + 1, mid + 1, right, updateLeft, updateRight, diff);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private void updateLazy(int node, int left, int right) {
        if (lazy[node] != 0) {
            tree[node] += lazy[node] * (right - left + 1);
            if (left != right) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        new SequenceAndQuery21().solution();
    }
}
