// https://www.acmicpc.net/problem/12844

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class XOR {

    private static int N, M, S;
    private static int[] A;
    private static int[] tree;
    private static int[] lazy;

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

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];
        lazy = new int[S * 2];

        init();

        M = Integer.parseInt(br.readLine());
        int op, i, j, k;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            op = Integer.parseInt(st.nextToken());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            if (i > j) {
                int temp = i;
                i = j;
                j = temp;
            }
            if (op == 1) {
                k = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, i, j, k);
            } else {
                bw.write(query(1, 0, S - 1, i, j) + "\n");
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
        return query(node * 2, left, mid, queryLeft, queryRight) ^
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private void update(int node, int left, int right, int updateLeft, int updateRight, int value) {
        updateLazy(node, left, right);
        if (updateRight < left || right < updateLeft) {
            return;
        }
        if (updateLeft <= left && right <= updateRight) {
            lazy[node] = value;
            updateLazy(node, left, right);
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, updateLeft, updateRight, value);
            update(node * 2 + 1, mid + 1, right, updateLeft, updateRight, value);
            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }
    }

    private void updateLazy(int node, int left, int right) {
        if (lazy[node] != 0) {
            if (left != right) {
                lazy[node * 2] ^= lazy[node];
                lazy[node * 2 + 1] ^= lazy[node];
            }
            if ((right - left + 1) % 2 == 1) {
                tree[node] ^= lazy[node];
            }
            lazy[node] = 0;
        }
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = A[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new XOR().solution();
    }
}
