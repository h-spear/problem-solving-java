// https://www.acmicpc.net/problem/16993

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class PrefixSumAndQuery {

    private static final int INF = (int) 1e9;
    private static int N, S;
    private static Pair[] tree;

    class Pair {
        int leftSum;
        int rightSum;
        int maxSum;
        int totalSum;

        public Pair() {
        }

        public Pair(int leftSum, int rightSum, int maxSum, int totalSum) {
            this.leftSum = leftSum;
            this.rightSum = rightSum;
            this.maxSum = maxSum;
            this.totalSum = totalSum;
        }
    }
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new Pair[S * 2];

        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
            tree[S + i] = new Pair(A[i], A[i], A[i], A[i]);
        }

        for (int i = N; i < S; ++i) {
            tree[S + i] = new Pair();
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = merge(tree[i * 2], tree[i * 2 + 1]);
        }

        int M = Integer.parseInt(br.readLine());
        int i, j;

        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            Pair res = query(1, 0, S - 1, i - 1, j - 1);
            bw.write(res.maxSum + " \n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private Pair query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return new Pair(-INF, -INF, -INF, 0);
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return merge(query(node * 2, left, mid, queryLeft, queryRight),
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    private Pair merge(Pair left, Pair right) {
        Pair temp = new Pair();
        temp.leftSum = Math.max(left.leftSum, left.totalSum + right.leftSum);
        temp.rightSum = Math.max(right.rightSum, left.rightSum + right.totalSum);
        temp.maxSum = Math.max(Math.max(left.maxSum, right.maxSum), left.rightSum + right.leftSum);
        temp.totalSum = left.totalSum + right.totalSum;
        return temp;
    }

    public static void main(String[] args) throws Exception {
        new PrefixSumAndQuery().solution();
    }
}
