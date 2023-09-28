// https://www.acmicpc.net/problem/25639

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndMaximumRiseQuery {

    private static class Node {
        static final Node outNode = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        long min;
        long max;
        long growth;

        Node(int num) {
            set(num);
        }

        private Node(long min, long max, long growth) {
            this.min = min;
            this.max = max;
            this.growth = growth;
        }

        void set(long num) {
            this.min = num;
            this.max = num;
            this.growth = 0;
        }

        Node merge(Node left, Node right) {
            this.min = Math.min(left.min, right.min);
            this.max = Math.max(left.max, right.max);
            this.growth = Math.max(
                    Math.max(left.growth, right.growth),
                    right.max - left.min
            );
            return this;
        }
    }

    private static int N, S;
    private static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int Q, c, a, b;

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
            tree[S + i] = Node.outNode;
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = new Node(0).merge(tree[i << 1], tree[(i << 1) | 1]);
        }

        Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (c == 1) {
                update(a - 1, b);
            } else {
                bw.write(query(1, 0, S - 1, a - 1, b - 1).growth + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static Node query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return Node.outNode;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return new Node(0).merge(query(node << 1, left, mid, queryLeft, queryRight),
                query((node << 1) | 1, mid + 1, right, queryLeft, queryRight));
    }

    private static void update(int target, int value) {
        int idx = S + target;
        tree[idx].set(value);
        idx >>= 1;
        while (idx > 0) {
            tree[idx].merge(tree[idx << 1], tree[(idx << 1) | 1]);
            idx >>= 1;
        }
    }
}
