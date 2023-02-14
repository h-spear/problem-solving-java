// https://www.acmicpc.net/problem/2104

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SubArraySelect {

    private static int N, S;
    private static long[] A;
    private static int[] idxTree;
    private static long[] sumTree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        idxTree = new int[S * 2];
        sumTree = new long[S * 2];

        initTrees();

        bw.write(findMaxScore(0, N - 1) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private long findMaxScore(int queryLeft, int queryRight) {
        int idx = idxQuery(1, 0, S - 1, queryLeft, queryRight);
        long sum = sumQuery(1, 0, S - 1, queryLeft, queryRight);
        long s1, s2 = 0, s3 = 0;

        s1 = sum * A[idx];
            if (idx > queryLeft) {
            s2 = findMaxScore(queryLeft, idx - 1);
        }
        if (idx < queryRight) {
            s3 = findMaxScore(idx + 1, queryRight);
        }
        return Math.max(Math.max(s1, s2), s3);
    }

    private long sumQuery(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return sumTree[node];
        }
        int mid = (left + right) / 2;
        return sumQuery(node * 2, left, mid, queryLeft, queryRight) +
                sumQuery(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private int idxQuery(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return -1;
        }
        if (queryLeft <= left && right <= queryRight) {
            return idxTree[node];
        }
        int mid = (left + right) / 2;
        int leftResult = idxQuery(node * 2, left, mid, queryLeft, queryRight);
        int rightResult = idxQuery(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
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

    private void initTrees() {
        for (int i = 0; i < N; ++i) {
            idxTree[S + i] = i;
            sumTree[S + i] = A[i];
        }
        for (int i = N; i < S; ++i) {
            idxTree[S + i] = -1;
        }

        for (int i = S - 1; i > 0; --i) {
            sumTree[i] = sumTree[i * 2] + sumTree[i * 2 + 1];

            if (idxTree[i * 2] == -1 && idxTree[i * 2 + 1] == -1) {
                idxTree[i] = -1;
            } else if (idxTree[i * 2] == -1) {
                idxTree[i] = idxTree[i * 2 + 1];
            } else if (idxTree[i * 2 + 1] == -1) {
                idxTree[i] = idxTree[i * 2];
            } else {
                if (A[idxTree[i * 2]] <= A[idxTree[i * 2 + 1]]) {
                    idxTree[i] = idxTree[i * 2];
                } else {
                    idxTree[i] = idxTree[i * 2 + 1];
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new SubArraySelect().solution();
    }
}
