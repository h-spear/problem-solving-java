// https://www.acmicpc.net/problem/16926

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class RotateArray1 {

    private static void rotate(int[][] A, int N, int M) {
        int top = 0, bottom = N - 1;
        int left = 0, right = M - 1;
        int temp;
        while (left < right && top < bottom) {
            temp = A[top][left];

            for (int j = left; j < right; ++j)
                A[top][j] = A[top][j + 1];

            for (int i = top; i < bottom; ++i)
                A[i][right] = A[i + 1][right];

            for (int j = right; j > left; --j)
                A[bottom][j] = A[bottom][j - 1];

            for (int i = bottom; i > top; --i)
                A[i][left] = A[i - 1][left];

            A[top + 1][left] = temp;
            ++top; ++left; --bottom; --right;
        }
    }

    private static void printArray(int[][] A, int N, int M) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j)
                sb.append(A[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (R-- > 0)
            rotate(A, N, M);
        printArray(A, N, M);
        br.close();
    }
}