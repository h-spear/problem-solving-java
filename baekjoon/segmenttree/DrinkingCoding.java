// https://www.acmicpc.net/problem/5676

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class DrinkingCoding {

    private static int N, K, S;
    private static int[] X;
    private static int[] tree;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input;
        while ((input = br.readLine()) != null) {

            st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            X = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                X[i] = Integer.parseInt(st.nextToken());
                if (X[i] != 0) {
                    X[i] /= Math.abs(X[i]);
                }
            }

            S = 1;
            while (S < N) {
                S <<= 1;
            }
            tree = new int[S * 2];

            init();

            char a;
            int i, j, V;
            for (int q = 0; q < K; ++q) {
                st = new StringTokenizer(br.readLine());
                a = st.nextToken().charAt(0);
                i = Integer.parseInt(st.nextToken());
                if (a == 'C') {
                    V = Integer.parseInt(st.nextToken());
                    update(1, 0, S - 1, i - 1, V);
                } else {
                    j = Integer.parseInt(st.nextToken());
                    int res = query(1, 0, S - 1, i - 1, j - 1);
                    if (res == 1) {
                        bw.write("+");
                    } else if (res == 0) {
                        bw.write("0");
                    } else {
                        bw.write("-");
                    }
                }
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 1;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, queryLeft, queryRight) *
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    private void update(int node, int left, int right, int target, int value) {
        if (target < left || right < target) {
            return;
        }
        if (left == right) {
            tree[node] = value;
            if (tree[node] != 0) {
                tree[node] /= Math.abs(tree[node]);
            }
        } else {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, value);
            update(node * 2 + 1, mid + 1, right, target, value);
            tree[node] = tree[node * 2] * tree[node * 2 + 1];
        }
    }

    private void init() {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = X[i];
        }

        for (int i = S - 1; i > 0; --i) {
            tree[i] = tree[i * 2] * tree[i * 2 + 1];
        }
    }

    public static void main(String[] args) throws Exception {
        new DrinkingCoding().solution();
    }
}
