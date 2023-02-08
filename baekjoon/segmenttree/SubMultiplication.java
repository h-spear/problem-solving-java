// https://www.acmicpc.net/problem/11505

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SubMultiplication {

    private static final int P = 1000000007;
    private int N, M, K, S;
    private int[] arr;
    private long[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];

        init();

        int a, b, c;
        for (int i = 0; i < M + K; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                update(1, 0, S - 1, b - 1, c);
            } else {
                bw.write("" + query(1, 0, S - 1, b - 1, c - 1));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = arr[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % P;
        }
    }

    private long query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 1;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = (left + right) / 2;
        return (query(node * 2, left, mid, queryLeft, queryRight) *
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight)) % P;
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[S + target] = value;
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % P;
        }
    }

    public static void main(String[] args) throws Exception {
        new SubMultiplication().solution();
    }
}
