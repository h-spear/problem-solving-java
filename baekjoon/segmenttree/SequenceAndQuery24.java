// https://www.acmicpc.net/problem/17408

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery24 {

    private static class Node {
        int max1, max2;

        Node(int num) {
            this.max1 = num;
            this.max2 = -1;
        }

        Node merge(Node left, Node right) {
            if (left.max1 > right.max1) {
                this.max1 = left.max1;
                this.max2 = Math.max(left.max2, right.max1);
            } else {
                this.max1 = right.max1;
                this.max2 = Math.max(left.max1, right.max2);
            }
            return this;
        }

        int getSum() {
            return max1 + max2;
        }
    }

    private static final Node outNode = new Node(-1);
    private static int S;
    private static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N, M, c, a, b;

        N = Integer.parseInt(br.readLine());
        S = 1;
        while (S < N)
            S <<= 1;
        tree = new Node[S << 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            tree[S + i] = new Node(Integer.parseInt(st.nextToken()));
        }
        for (int i = N; i < S; ++i) {
            tree[S + i] = outNode;
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = new Node(0).merge(tree[i << 1], tree[(i << 1) | 1]);
        }

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (c == 1) {
                update(a - 1, b);
            } else {
                bw.write(query(1, 0, S - 1, a - 1, b - 1).getSum() + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static Node query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return outNode;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return new Node(-1).merge(query(node << 1, left, mid, queryLeft, queryRight),
                query((node << 1) | 1, mid + 1, right, queryLeft, queryRight));
    }

    private static void update(int target, int value) {
        int idx = S + target;
        tree[idx].max1 = value;
        tree[idx].max2 = -1;

        idx >>= 1;
        while (idx > 0) {
            tree[idx].merge(tree[idx << 1], tree[(idx << 1) | 1]);
            idx >>= 1;
        }
    }
}
