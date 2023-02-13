// https://www.acmicpc.net/problem/3653

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class MovieCollection {

    private static int N, M, S;
    private static int top;
    private static int[] arr;
    private static int[] tree;
    private static Map<Integer, Integer> map;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new HashMap<>();
            // key: DVD 번호, value: arr에서 해당 DVD의 index
            for (int i = 0; i < N; ++i) {
                map.put(N - i, i);
            }

            arr = new int[N + M + 1];
            S = 1;
            while (S < N + M + 1) {
                S <<= 1;
            }
            tree = new int[S * 2];

            init();

            top = N;

            // query
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; ++i) {
                int d = Integer.parseInt(st.nextToken());
                int target = map.get(d);
                bw.write(query(1, 0, S - 1, target + 1, N + M) + " ");
                update(1, 0, S - 1, target, 0);
                update(1, 0, S - 1, top, 1);
                map.put(d, top++);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, queryLeft, queryRight) +
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
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

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = 1;
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new MovieCollection().solution();
    }
}
