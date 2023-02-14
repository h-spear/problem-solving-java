// https://www.acmicpc.net/problem/9345

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class DigitalVideoDisc {

    private static int N, K, S;
    private static int[] minTree;
    private static int[] maxTree;
    private static int[] bucket;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T, Q, A, B;

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            bucket = new int[N];
            for (int i = 0; i < N; ++i) {
                bucket[i] = i;
            }

            S = 1;
            while (S < N) {
                S <<= 1;
            }
            minTree = new int[S * 2];
            maxTree = new int[S * 2];

            initTrees();

            for (int i = 0; i < K; ++i) {
                st = new StringTokenizer(br.readLine());
                Q = Integer.parseInt(st.nextToken());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                if (Q == 0) {
                    int dvdA = bucket[A];
                    int dvdB = bucket[B];
                    bucket[A] = dvdB;
                    bucket[B] = dvdA;
                    updateMinTree(1, 0, S - 1, A, dvdB);
                    updateMinTree(1, 0, S - 1, B, dvdA);
                    updateMaxTree(1, 0, S - 1, A, dvdB);
                    updateMaxTree(1, 0, S - 1, B, dvdA);
                } else {
                    int min = queryMinTree(1, 0, S - 1, A, B);
                    int max = queryMaxTree(1, 0, S - 1, A, B);
                    if (min == A && max == B) {
                        bw.write("YES\n");
                    } else {
                        bw.write("NO\n");
                    }
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int queryMaxTree(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return -1;
        }
        if (queryLeft <= left && right <= queryRight) {
            return maxTree[node];
        }
        int mid = (left + right) / 2;
        return Math.max(queryMaxTree(node * 2, left, mid, queryLeft, queryRight),
                queryMaxTree(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    private int queryMinTree(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return Integer.MAX_VALUE;
        }
        if (queryLeft <= left && right <= queryRight) {
            return minTree[node];
        }
        int mid = (left + right) / 2;
        return Math.min(queryMinTree(node * 2, left, mid, queryLeft, queryRight),
                queryMinTree(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    private void updateMinTree(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            minTree[node] = value;
        } else {
            int mid = (left + right) / 2;
            updateMinTree(node * 2, left, mid, target, value);
            updateMinTree(node * 2 + 1, mid + 1, right, target, value);
            minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
        }
    }

    private void updateMaxTree(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            maxTree[node] = value;
        } else {
            int mid = (left + right) / 2;
            updateMaxTree(node * 2, left, mid, target, value);
            updateMaxTree(node * 2 + 1, mid + 1, right, target, value);
            maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        }
    }

    private void initTrees() {
        for (int i = 0; i < N; ++i) {
            minTree[S + i] = bucket[i];
            maxTree[S + i] = bucket[i];
        }
        for (int i = N; i < S; ++i) {
            minTree[S + i] = Integer.MAX_VALUE;
            maxTree[S + i] = -1;
        }
        for (int i = S - 1; i > 0; --i) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }
    }

    public static void main(String[] args) throws Exception {
        new DigitalVideoDisc().solution();
    }
}
