// https://www.acmicpc.net/problem/6549
// indexed tree + divide and conquer

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class LargestRectangleInHistogram {

    private static int N, S;
    private static int[] H;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if (N == 0)
                break;

            H = new int[N];
            for (int i = 0; i < N; ++i) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            S = 1;
            while (S < N) {
                S <<= 1;
            }
            tree = new int[S * 2];

            init();
            bw.write("" + divideAndConquer(0, N - 1));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 최소 인덱스를 기준으로 분할정복
    private long divideAndConquer(int queryLeft, int queryRight) {
        if (queryLeft == queryRight) {
            return H[tree[S + queryLeft]];
        }
        long h1, h2 = 0, h3 = 0;
        int minIdx = query(1, 0, S - 1, queryLeft, queryRight);
        h1 = (long) H[minIdx] * (queryRight - queryLeft + 1);
        if (queryLeft <= minIdx - 1) {
            h2 = divideAndConquer(queryLeft, minIdx - 1);
        }
        if (queryRight >= minIdx + 1) {
            h3 = divideAndConquer(minIdx + 1, queryRight);
        }
        return Math.max(Math.max(h1, h2), h3);
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
            if (H[leftResult] <= H[rightResult]) {
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
            if (tree[i * 2] == -1) {
                tree[i] = -1;
            } else if (tree[i * 2 + 1] == -1) {
                tree[i] = tree[i * 2];
            } else {
                if (H[tree[i * 2]] <= H[tree[i * 2 + 1]]) {
                    tree[i] = tree[i * 2];
                } else {
                    tree[i] = tree[i * 2 + 1];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new LargestRectangleInHistogram().solution();
    }
}
