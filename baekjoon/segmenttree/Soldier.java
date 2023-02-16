// https://www.acmicpc.net/problem/1321

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Soldier {

    private static int N, S;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = 1;
        while (S <= N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        // init
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            tree[S + i] = Integer.parseInt(st.nextToken());
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        int M = Integer.parseInt(br.readLine());

        int i, a, c;
        for (int q = 0; q < M; ++q) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            i = Integer.parseInt(st.nextToken());
            if (c == 1) {
                a = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, i, a);
            } else {
                bw.write(query(1, 0, S - 1, i) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int query(int node, int left, int right, int rank) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) >> 1;
            if (tree[node * 2] >= rank) {
                return query(node * 2, left, mid, rank);
            } else {
                return query(node * 2 + 1, mid + 1, right, rank - tree[node * 2]);
            }
        }
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += value;
        if (left != right) {
            int mid = (left + right) >> 1;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
        }
    }

    public static void main(String[] args) throws Exception {
        new Soldier().solution();
    }
}
