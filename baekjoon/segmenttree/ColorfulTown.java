// https://www.acmicpc.net/problem/12895

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class ColorfulTown {

    private static int S;
    private static int[] tree;
    private static int[] lazy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N)
            S <<= 1;
        tree = new int[S << 1];
        lazy = new int[S << 1];

        Arrays.fill(tree, S, S + N, 1);
        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i << 1] & tree[(i << 1) | 1];
        }

        char c;
        int x, y, z;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            c = st.nextToken().charAt(0);
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            if (c == 'C') {
                z = Integer.parseInt(st.nextToken()) - 1;
                update(x, y, z);
            } else {
                bw.write(query(x, y) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int query(int x, int y) {
        int i = query(1, 0, S - 1, x, y);
        int res = 0;
        while (i > 0) {
            if ((i & 1) == 1)
                ++res;
            i >>= 1;
        }
        return res;
    }

    private static int query(int node, int left, int right, int queryLeft, int queryRight) {
        propagate(node, left, right);
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return query(node << 1, left, mid, queryLeft, queryRight) |
                query((node << 1) | 1, mid + 1, right, queryLeft, queryRight);
    }

    private static void update(int x, int y, int z) {
        update(1, 0, S - 1, x, y, 1 << z);
    }

    private static void update(int node, int left, int right, int updateLeft, int updateRight, int value) {
        propagate(node, left, right);
        if (updateRight < left || right < updateLeft) {
            return;
        }
        if (updateLeft <= left && right <= updateRight) {
            lazy[node] = value;
            propagate(node, left, right);
            return;
        }
        int mid = (left + right) >> 1;
        update(node << 1, left, mid, updateLeft, updateRight, value);
        update((node << 1) | 1, mid + 1, right, updateLeft, updateRight, value);
        tree[node] = tree[node << 1] | tree[(node << 1) | 1];
    }

    private static void propagate(int node, int left, int right) {
        if (lazy[node] > 0) {
            tree[node] = lazy[node];
            if (left != right) {
                lazy[node << 1] = lazy[node];
                lazy[(node << 1) | 1] = lazy[node];
            }
            lazy[node] = 0;
        }
    }
}
