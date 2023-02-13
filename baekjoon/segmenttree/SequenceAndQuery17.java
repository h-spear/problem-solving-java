// https://www.acmicpc.net/problem/14438

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery17 {

    private static int N, M, S;
    private static int[] A;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        init();

        M = Integer.parseInt(br.readLine());
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            if (a == 1) {
                int v = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, i - 1, v);
            } else {
                int j = Integer.parseInt(st.nextToken());
                bw.write(query(1, 0, S - 1, i - 1, j - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[node] = value;
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return Integer.MAX_VALUE;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        int leftResult = query(node * 2, left, mid, queryLeft, queryRight);
        int rightResult = query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
        return Math.min(leftResult, rightResult);
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = A[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    public static void main(String[] args) throws Exception {
        new SequenceAndQuery17().solution();
    }
}
