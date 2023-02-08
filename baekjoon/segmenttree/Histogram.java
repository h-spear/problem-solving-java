// https://www.acmicpc.net/problem/1725

package baekjoon.segmenttree;

import java.io.*;

public class Histogram {

    private int N, S;
    private int[] H;
    private int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        H = new int[N];
        for (int i = 0; i < N; ++i) {
            H[i] = Integer.parseInt(br.readLine());
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];      // index

        init();

        bw.write("" + getMaximumArea(0, N - 1));

        bw.flush();
        bw.close();
        br.close();
    }

    private long getMaximumArea(int left, int right) {
        int idx = query(1, 0, S - 1, left, right);
        long h1, h2 = 0, h3 = 0;

        h1 = H[idx] * (right - left + 1);
        if (idx - 1 >= left) {
            h2 = getMaximumArea(left, idx - 1);
        }
        if (idx + 1 <= right) {
            h3 = getMaximumArea(idx + 1, right);
        }
        return Math.max(Math.max(h1, h2), h3);
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = i;
        }
        for (int i = N; i < S; ++i) {
            tree[S + i] = -1;   // 인덱스가 없음
        }

        for (int i = S - 1; i > 0; --i) {
            if (tree[i * 2] == -1 && tree[i * 2 + 1] == -1) {
                tree[i] = -1;
            } else if (tree[i * 2] == -1) {
                tree[i] = tree[i * 2 + 1];
            } else if (tree[i * 2 + 1] == -1) {
                tree[i] = tree[i * 2];
            } else {
                if (H[tree[i * 2]] < H[tree[i * 2 + 1]]) {
                    tree[i] = tree[i * 2];
                } else {
                    tree[i] = tree[i * 2 + 1];
                }
            }
        }
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
            if (H[leftResult] < H[rightResult]) {
                return leftResult;
            } else {
                return rightResult;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Histogram().solution();
    }
}
