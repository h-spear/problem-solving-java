// https://www.acmicpc.net/problem/18436

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery37 {

    private static int N, M, S;
    private static int[] A;
    private static int[] oddTree;
    private static int[] evenTree;

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

        M = Integer.parseInt(br.readLine());

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        oddTree = new int[S * 2];
        evenTree = new int[S * 2];

        initTrees();

        int a, i, x, l, r;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            if (a == 1) {
                i = Integer.parseInt(st.nextToken()) - 1;
                x = Integer.parseInt(st.nextToken());
                if ((A[i] & 1) == 1 && (x & 1) == 0) {
                    update(oddTree, 1, 0, S - 1, i, 0);
                    update(evenTree, 1, 0, S - 1, i, 1);
                } else if ((A[i] & 1) == 0 && (x & 1) == 1) {
                    update(oddTree, 1, 0, S - 1, i, 1);
                    update(evenTree, 1, 0, S - 1, i, 0);
                }
                A[i] = x;   // A 배열에서도 변경
            } else {
                l = Integer.parseInt(st.nextToken()) - 1;
                r = Integer.parseInt(st.nextToken()) - 1;
                if (a == 2) {
                    bw.write(query(evenTree, 1, 0, S - 1, l, r) + "\n");
                } else {
                    bw.write(query(oddTree, 1, 0, S - 1, l, r) + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int query(int[] tree, int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(tree, node * 2, left, mid, queryLeft, queryRight) +
                query(tree, node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private void update(int[] tree, int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[node] = value;
        } else {
            int mid = (left + right) / 2;
            update(tree, node * 2, left, mid, target, value);
            update(tree, node * 2 + 1, mid + 1, right, target, value);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private void initTrees() {
        for (int i = 0; i < N; ++i) {
            if ((A[i] & 1) == 1) {
                oddTree[S + i] = 1;
            } else {
                evenTree[S + i] = 1;
            }
        }

        for (int i = S - 1; i > 0; --i) {
            oddTree[i] = oddTree[i * 2] + oddTree[i * 2 + 1];
            evenTree[i] = evenTree[i * 2] + evenTree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new SequenceAndQuery37().solution();
    }
}
