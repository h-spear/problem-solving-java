// https://www.acmicpc.net/problem/2243

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class CandyBox {

    private static int S;
    private static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, A, B, C;

        n = Integer.parseInt(br.readLine());

        S = 1;
        while (S < 1000000) {
            S <<= 1;
        }
        tree = new int[S * 2];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (A == 1) {
                int candy = query(1, 0, S - 1, B);
                System.out.println(candy);
                update(1, 0, S - 1, candy - 1, -1);
            } else {
                C = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, B - 1, C);
            }
        }

        br.close();
    }

    private static int query(int node, int left, int right, int prio) {
        if (left == right) {
            return left + 1;
        }
        // 왼쪽에 존재
        int mid = (left + right) / 2;
        if (tree[node * 2] >= prio) {
            return query(node * 2, left, mid, prio);
        } else {
            return query(node * 2 + 1, mid + 1, right, prio - tree[node * 2]);
        }
    }

    private static void update(int node, int left, int right, int target, int diff) {
        if (target < left || target > right) {
            return;
        }

        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, diff);
            update(node * 2 + 1, mid + 1, right, target, diff);
        }
    }
}
