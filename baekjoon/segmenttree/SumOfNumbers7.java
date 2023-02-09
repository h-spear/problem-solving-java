// https://www.acmicpc.net/problem/2268

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SumOfNumbers7 {

    private int N, M, S;
    private long[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new long[S * 2];

        int c, i, j, k;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            if (c == 0) {
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());
                if (i < j) {
                    bw.write(query(1, 0, S - 1, i - 1, j - 1) + "\n");
                } else {
                    bw.write(query(1, 0, S - 1, j - 1, i - 1) + "\n");
                }
            } else {
                i = Integer.parseInt(st.nextToken());
                k = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, i - 1, k);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private long query(int node, int left, int right, int queryLeft, int queryRight) {
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
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new SumOfNumbers7().solution();
    }
}
