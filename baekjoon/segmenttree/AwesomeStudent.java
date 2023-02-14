// https://www.acmicpc.net/problem/2336

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class AwesomeStudent {

    private static int N, S;
    private static int[] A;
    private static int[] B;
    private static int[] C;
    private static int[] tree;
    private static List<Pair> list = new ArrayList<>(500001);

    class Pair implements Comparable<Pair>{
        int x, y, z;

        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Pair o2) {
            int comp1 = this.x - o2.x;
            if (comp1 == 0) {
                int comp2 = this.y - o2.y;
                if (comp2 == 0) {
                    return this.z - o2.z;
                } else {
                    return comp2;
                }
            } else {
                return comp1;
            }
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];

        int r;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            r = Integer.parseInt(st.nextToken()) - 1;
            A[r] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            r = Integer.parseInt(st.nextToken()) - 1;
            B[r] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            r = Integer.parseInt(st.nextToken()) - 1;
            C[r] = i;
        }

        for (int i = 0; i < N; ++i) {
            list.add(new Pair(A[i], B[i], C[i]));
        }

        Collections.sort(list);

        // segment tree
        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        Arrays.fill(tree, Integer.MAX_VALUE);

        int answer = 0;
        for (Pair p: list) {
            int ranker = query(1, 0, S - 1, 0, p.y - 1);
            if (p.z < ranker) {
                answer++;
            }
            update(1, 0, S - 1, p.y, p.z);
        }
        bw.write(answer + "");

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
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return Integer.MAX_VALUE;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return Math.min(query(node * 2, left, mid, queryLeft, queryRight),
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    public static void main(String[] args) throws Exception {
        new AwesomeStudent().solution();
    }
}
