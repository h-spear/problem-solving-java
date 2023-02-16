// https://www.acmicpc.net/problem/12846

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class ScaryPartTimeJob {

    private static int N, S;
    private static int[] T;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        T = new int[N];

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            T[i] = Integer.parseInt(st.nextToken());
        }

        init();
        bw.write("" + getMaximumProfit(0, N - 1));
        bw.flush();
        bw.close();
        br.close();
    }

    private long getMaximumProfit(int left, int right) {
        int idx = query(1, 0, S - 1, left, right);
        long p1, p2 = 0, p3 = 0;
        p1 = T[idx] * (right - left + 1);
        if (idx > left) {
            p2 = getMaximumProfit(left, idx - 1);
        }
        if (idx < right) {
            p3 = getMaximumProfit(idx + 1, right);
        }
        return Math.max(Math.max(p1, p2), p3);
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return -1;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        int leftResult = query(node * 2, left, mid, queryLeft, queryRight);
        int rightResult = query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
        if (leftResult == -1 && rightResult == -1) {
            return -1;
        } else if (leftResult == -1) {
            return rightResult;
        } else if (rightResult == -1) {
            return leftResult;
        } else {
            if (T[leftResult] <= T[rightResult]) {
                return leftResult;
            } else {
                return rightResult;
            }
        }
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = i;
        }
        for (int i = N; i < S; ++i) {
            tree[S + i] = -1;
        }
        for (int i = S - 1; i > 0; --i) {
            if (tree[i * 2] == -1 && tree[i * 2 + 1] == -1) {
                tree[i] = -1;
            } else if (tree[i * 2] == -1) {
                tree[i] = tree[i * 2 + 1];
            } else if (tree[i * 2 + 1] == -1) {
                tree[i] = tree[i * 2];
            } else {
                if (T[tree[i * 2]] <= T[tree[i * 2 + 1]]) {
                    tree[i] = tree[i * 2];
                } else {
                    tree[i] = tree[i * 2 + 1];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ScaryPartTimeJob().solution();
    }
}
