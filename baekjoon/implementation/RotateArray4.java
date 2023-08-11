// https://www.acmicpc.net/problem/17406

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class RotateArray4 {

    static class Pair {
        int r, c, s;

        public Pair(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    private static int N, M, K;
    private static int[][] A;
    private static int[] perm;
    private static Pair[] oper;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int r, c, s;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm = new int[K];
        oper = new Pair[K];
        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            oper[i] = new Pair(r, c, s);
        }

        dfs(0, 0);
        System.out.println(answer);
        br.close();
    }

    private static void dfs(int depth, int visit) {
        if (depth == K) {
            int[][] copied = deepCopy(A);
            for (int i = 0; i < K; ++i) {
                Pair p = oper[perm[i]];
                rotate(copied, p.r, p.c, p.s);
            }
            answer = Math.min(answer, getAnswer(copied));
            return;
        }

        for (int i = 0; i < K; ++i) {
            if ((visit & (1 << i)) != 0)
                continue;
            perm[depth] = i;
            dfs(depth + 1, visit | (1 << i));
        }
    }

    private static int[][] deepCopy(int[][] A) {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                copied[i][j] = A[i][j];
            }
        }
        return copied;
    }

    private static int getAnswer(int[][] A) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
            int temp = 0;
            for (int j = 0; j < M; ++j)
                temp += A[i][j];
            res = Math.min(res, temp);
        }
        return res;
    }

    private static void rotate(int[][] A, int r, int c, int s) {
        int left = c - s, right = c + s;
        int top = r - s, bottom = r + s;
        int temp;

        while (left < right && top < bottom) {
            temp = A[top][left];

            for (int i = top; i < bottom; ++i)
                A[i][left] = A[i + 1][left];

            for (int j = left; j < right; ++j)
                A[bottom][j] = A[bottom][j + 1];

            for (int i = bottom; i > top; --i)
                A[i][right] = A[i - 1][right];

            for (int j = right; j > left; --j)
                A[top][j] = A[top][j - 1];

            A[top][left + 1] = temp;

            ++top; --bottom; ++left; --right;
        }
    }
}