// https://www.acmicpc.net/problem/15561

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SubSumMaximum2 {

    private static class Node {
        int leftSum;
        int rightSum;
        int maxSum;
        int totalSum;

        Node() {}

        Node(int leftSum, int rightSum, int maxSum, int totalSum) {
            this.leftSum = leftSum;
            this.rightSum = rightSum;
            this.maxSum = maxSum;
            this.totalSum = totalSum;
        }

        void set(int value) {
            this.leftSum = value;
            this.rightSum = value;
            this.maxSum = value;
            this.totalSum = value;
        }

        Node merge(Node left, Node right) {
            this.leftSum = Math.max(left.leftSum, left.totalSum + right.leftSum);
            this.rightSum = Math.max(right.rightSum, left.rightSum + right.totalSum);
            this.maxSum = Math.max(Math.max(left.maxSum, right.maxSum), left.rightSum + right.leftSum);
            this.totalSum = left.totalSum + right.totalSum;
            return this;
        }
    }

    private static final int INF = Integer.MAX_VALUE >> 2;
    private static final Node outNode = new Node(-INF, -INF, -INF, 0);
    private static int N, S, U, V;
    private static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        S = 1;
        while (S < N)
            S <<= 1;
        tree = new Node[S << 1];

        int[] K = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            K[i] = Integer.parseInt(st.nextToken());
        }

        initialize(K);

        int C, A, B;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (C == 0) {
                bw.write((query(1, 0, S - 1, A - 1, B - 1).maxSum - V) + "\n");
            } else {
                update(1, 0, S - 1, A - 1, B);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static Node query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return outNode;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return new Node().merge(query(node << 1, left, mid, queryLeft, queryRight),
                query((node << 1) | 1, mid + 1, right, queryLeft, queryRight));
    }

    private static void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[node].set(U * value + V);
        } else {
            int mid = (left + right) >> 1;
            update(node << 1, left, mid, target, value);
            update((node << 1) | 1, mid + 1, right, target, value);
            tree[node].merge(tree[node << 1], tree[(node << 1) | 1]);
        }
    }

    private static void initialize(int[] arr) {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = new Node();
            tree[S + i].set(U * arr[i] + V);
        }
        for (int i = N; i < S; ++i) {
            tree[S + i] = outNode;
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = new Node().merge(tree[i << 1], tree[(i << 1) | 1]);
        }
    }
}
