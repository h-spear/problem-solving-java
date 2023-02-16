// https://www.acmicpc.net/problem/1777

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class PermutationRestoration {

    private static int N, S;
    private static int[] inversion;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        init();

        inversion = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            inversion[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        for (int i = N - 1; i >= 0; --i) {
            int idx = query(1, 0, S - 1, inversion[i] + 1);
            answer[idx] = i + 1;
            update(1, 0, S - 1, idx, 0);
        }

        for (int i = 0; i < N; ++i) {
            bw.write(answer[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = 1;
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[node] = value;
        } else {
            int mid = (left + right) >> 1;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    // 뒤에서 부터 랭킹
    private int query(int node, int left, int right, int rank) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) >> 1;
            if (tree[node * 2 + 1] >= rank) {
                return query(node * 2 + 1, mid + 1, right, rank);
            } else {
                return query(node * 2, left, mid, rank - tree[node * 2 + 1]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new PermutationRestoration().solution();
    }
}
