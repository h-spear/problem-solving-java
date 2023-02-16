// https://www.acmicpc.net/problem/17353

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class StarFallingFromTheSky {

    private static int N, S, H;
    private static int[] arr;
    private static long[] tree;
    private static long[] propagate;
    private static int[] counter;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        S = 1;
        H = 1;
        while (S < N) {
            S <<= 1;
            ++H;
        }

        tree = new long[S * 2];
        propagate = new long[S * 2];
        counter = new int[S * 2];

        int Q, L, R, X, C;

        initTrees();

        Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            if (C == 1) {
                L = Integer.parseInt(st.nextToken()) - 1;
                R = Integer.parseInt(st.nextToken()) - 1;
                update(1, 0, S - 1, L, R, 1);
            } else {
                X = Integer.parseInt(st.nextToken()) - 1;
                bw.write(query(1, 0, S - 1, X) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private long query(int node, int left, int right, int target) {
        lazyPropagate(node, left, right);
        if (target < left || right < target) {
            return 0;
        }
        if (left == right) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, target) +
                query(node * 2 + 1, mid + 1, right, target);
    }

    private void update(int node, int left, int right, int queryLeft, int queryRight, int value) {
        lazyPropagate(node, left, right);
        if (queryRight < left || right < queryLeft) {
            return;
        }
        if (queryLeft <= left && right <= queryRight) {
            counter[node] = 1;
            propagate[node] = value;
            lazyPropagate(node, left, right);
        } else {
            int mid = (left + right) / 2;
            int leftCount = Math.max(Math.min(queryRight, mid) - Math.max(queryLeft, left) + 1, 0);
            update(node * 2, left, mid, queryLeft, queryRight, value);
            update(node * 2 + 1, mid + 1, right, queryLeft, queryRight, leftCount + value);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private void lazyPropagate(int node, int left, int right) {
        if (propagate[node] != 0) {
            tree[node] += getStars(propagate[node], right - left + 1);
            if (left != right) {
                propagate[node * 2] += propagate[node];
                propagate[node * 2 + 1] += getLeafChildCount(node * 2) * counter[node] + propagate[node];
                counter[node * 2] += counter[node];
                counter[node * 2 + 1] += counter[node];
            }
            propagate[node] = 0;
            counter[node] = 0;
        }
    }

    private void initTrees() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = arr[i];
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private long getStars(long left, int length) {
        return left * length + (length * (length - 1)) / 2;
    }

    private int getLeafChildCount(int node) {
        int h = getHeight(node);
        return (int) Math.pow(2, h - 1);
    }

    private int getHeight(int node) {
        int i = 0;
        while (node >= (1 << i)) {
            ++i;
        }
        return H - i + 1;
    }

    public static void main(String[] args) throws Exception {
        new StarFallingFromTheSky().solution();
    }
}
