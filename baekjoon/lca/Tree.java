// https://www.acmicpc.net/problem/10838

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class Tree {

    private static final int MAX_CNT = 1001;

    private static int N, K;
    private static int[] color;
    private static int[] parent;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N];
        parent = new int[N];
        parent[0] = -1;

        int r, a, b, c;
        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (r == 1) {
                c = Integer.parseInt(st.nextToken());
                paint(a, b, c);
            } else if (r == 2) {
                move(a, b);
            } else {
                bw.write(count(a, b) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int findLCA(int a, int b) {
        boolean[] check = new boolean[N];

        int count = 0;
        int lca = 0;
        // 문제에 a - b사이의 길이가 1,000이하라고 주어졌음. 그 이상을 탐색할 필요 X(시간초과 해결)
        while (parent[a] != -1 && count++ < MAX_CNT) {
            check[a] = true;
            a = parent[a];
        }

        count = 0;
        while (parent[b] != -1 && count++ < MAX_CNT) {
            if (check[b]) {
                lca = b;
                break;
            }
            b = parent[b];
        }
        return lca;
    }

    private int count(int a, int b) {
        Set<Integer> set = new HashSet<>();
        int lca = findLCA(a, b);

        while (a != lca) {
            set.add(color[a]);
            a = parent[a];
        }
        while (b != lca) {
            set.add(color[b]);
            b = parent[b];
        }
        return set.size();
    }

    private void move(int a, int b) {
        parent[a] = b;
    }

    private void paint(int a, int b, int c) {
        int lca = findLCA(a, b);

        while (a != lca) {
            color[a] = c;
            a = parent[a];
        }
        while (b != lca) {
            color[b] = c;
            b = parent[b];
        }
    }

    public static void main(String[] args) throws Exception {
        new Tree().solution();
    }
}
