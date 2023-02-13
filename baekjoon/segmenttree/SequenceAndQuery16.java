// https://www.acmicpc.net/problem/14428

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery16 {

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

        int c, i, j, v;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            i = Integer.parseInt(st.nextToken());
            if (c == 1) {
                v = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, i - 1, v);
            } else {
                j = Integer.parseInt(st.nextToken());
                bw.write((query(1, 0, S - 1, i - 1, j - 1) + 1)+ "\n");
            }
        }


        bw.flush();
        bw.close();
        br.close();
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
            if (A[leftResult] <= A[rightResult]) {
                return leftResult;
            } else {
                return rightResult;
            }
        }
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right <target) {
            return;
        }
        if (left == right) {
            A[left] = value;
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
            if (tree[node * 2] == -1 && tree[node * 2 + 1] == -1) {
                tree[node] = -1;
            } else if (tree[node * 2] == -1) {
                tree[node] = tree[node * 2 + 1];
            } else if (tree[node * 2 + 1] == -1) {
                tree[node] = tree[node * 2];
            } else {
                if (A[tree[node * 2]] <= A[tree[node * 2 + 1]]) {
                    tree[node] = tree[node * 2];
                } else {
                    tree[node] = tree[node * 2 + 1];
                }
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
                if (A[tree[i * 2]] <= A[tree[i * 2 + 1]]) {
                    tree[i] = tree[i * 2];
                } else {
                    tree[i] = tree[i * 2 + 1];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new SequenceAndQuery16().solution();
    }
}
