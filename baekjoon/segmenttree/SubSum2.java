// https://www.acmicpc.net/problem/10999
// Segment Tree with Lazy Propagation

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SubSum2 {

    private int N, M, K, S;
    private long[] arr;
    private long[] tree;
    private long[] lazy;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = Long.parseLong(br.readLine());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];
        lazy = new long[S * 2];

        init();

        int a, b, c;
        long d;
        for(int i = 0; i < M + K; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                d = Long.parseLong(st.nextToken());
                update(1, 0, S - 1, b - 1, c - 1, d);
            } else {
                bw.write(query(1, 0, S - 1, b - 1, c - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
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

    private long query(int node, int left, int right, int queryLeft, int queryRight) {
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

    private void update(int node, int left, int right, int updateLeft, int updateRight, long diff) {
        updateLazy(node, left, right);
        if (updateRight < left || right < updateLeft) {
            return;
        }
        if (updateLeft <= left && right <= updateRight) {
            tree[node] += diff * (right - left + 1);

            // 완전히 겹치면 업데이트를 진행하지 않음
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

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = arr[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new SubSum2().solution();
    }
}
