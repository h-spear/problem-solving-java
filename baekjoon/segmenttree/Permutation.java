// https://www.acmicpc.net/problem/1849

package baekjoon.segmenttree;

import java.io.*;

public class Permutation {

    private static int N, S;
    private static int[] answer;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        S = 1;
        while (S <= N) {
            S <<= 1;
        }
        answer = new int[N + 1];
        tree = new int[S * 2];

        initialize();

        for (int i = 1; i <= N; ++i) {
            int A = Integer.parseInt(br.readLine());
            int idx = query(1, 0, S - 1, A + 1);
            answer[idx] = i;
            update(1, 0, S - 1, idx, 0);
        }

        for (int i = 1; i <= N; ++i) {
            bw.write(answer[i] + "\n");
        }

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

    private int query(int node, int left, int right, int rank) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= rank) {
                return query(node * 2, left, mid, rank);
            } else {
                return query(node * 2 + 1, mid + 1, right, rank - tree[node * 2]);
            }
        }
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
        new Permutation().solution();
    }
}
