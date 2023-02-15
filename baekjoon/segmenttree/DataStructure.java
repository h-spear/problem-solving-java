// https://www.acmicpc.net/problem/12899

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class DataStructure {

    private static int S;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N, T, X;

        S = 1;
        while (S <= 2000000) {
            S <<= 1;
        }
        tree = new int[S * 2];

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            if (T == 1) {
                update(1, 0, S - 1, X, 1);
            } else {
                int res = query(1, 0, S - 1, X);
                bw.write(res + "\n");
                update(1, 0, S - 1, res, -1);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int query(int node, int left, int right, int prio) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= prio) {
                return query(node * 2, left, mid, prio);
            } else {
                return query(node * 2 + 1, mid + 1, right, prio - tree[node * 2]);
            }
        }
    }

    private void update(int node, int left, int right, int target, int diff) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, diff);
            update(node * 2 + 1, mid + 1, right, target, diff);
        }
    }

    public static void main(String[] args) throws Exception {
        new DataStructure().solution();
    }
}
