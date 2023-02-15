// https://www.acmicpc.net/problem/1168

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class JosephusProblem2 {

    private static int N, K, S;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb;

        sb = new StringBuilder("");
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while (S <= N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        initialize();

        sb.append("<");

        int count, leftCount, rightCount;
        int x = 0, r;
        for (int i = 0; i < N; ++i) {
            count = countQuery(1, 0, S - 1, 0, N);
            rightCount = countQuery(1, 0, S - 1, x, N); // x ~ END
            leftCount = count - rightCount; // START ~ x-1
            r = K % count == 0 ? count : K % count;
            if (rightCount >= r) {
                x = rankQuery(1, 0, S - 1, leftCount + r);
            } else {
                x = rankQuery(1, 0, S - 1, r - rightCount);
            }
            sb.append(x);
            if (i != N - 1) {
                sb.append(", ");
            }
            update(1, 0, S - 1, x, 0);
        }
        sb.append(">");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
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

    private int rankQuery(int node, int left, int right, int rank) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= rank) {
                return rankQuery(node * 2, left, mid, rank);
            } else {
                return rankQuery(node * 2 + 1, mid + 1, right, rank - tree[node * 2]);
            }
        }
    }

    private int countQuery(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return countQuery(node * 2, left, mid, queryLeft, queryRight) +
                countQuery(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private void initialize() {
        for (int i = 1; i <= N; ++i) {
            tree[S + i] = 1;
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new JosephusProblem2().solution();
    }
}
