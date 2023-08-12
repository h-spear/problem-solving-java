// https://www.acmicpc.net/problem/16935

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class RotateArray3 {

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

        st = new StringTokenizer(br.readLine());
        while (R-- > 0) {
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    A = flipVertical(A, N, M); break;
                case 2:
                    A = flipHorizontal(A, N, M); break;
                case 3:
                    A = rotateDegree90(A, N, M); break;
                case 4:
                    A = rotateDegree270(A, N, M); break;
                case 5:
                    A = operation5(A, N, M); break;
                case 6:
                    A = operation6(A, N, M); break;
            }
            N = A.length;
            M = A[0].length;
        }
        printArray(A, N, M);
        br.close();
    }

    private static int[][] flipVertical(int[][] A, int N, int M) {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                copied[N - i - 1][j] = A[i][j];
            }
        }
        return copied;
    }

    private static int[][] flipHorizontal(int[][] A, int N, int M) {
        int[][] copied = new int[N][M];
        for (int j = 0; j < M; ++j) {
            for (int i = 0; i < N; ++i) {
                copied[i][M - j - 1] = A[i][j];
            }
        }
        return copied;
    }

    private static int[][] rotateDegree90(int[][] A, int N, int M) {
        int[][] copied = new int[M][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                copied[j][N - i - 1] = A[i][j];
            }
        }
        return copied;
    }

    private static int[][] rotateDegree270(int[][] A, int N, int M) {
        int[][] copied = new int[M][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                copied[M - j - 1][i] = A[i][j];
            }
        }
        return copied;
    }

    private static int[][] operation5(int[][] A, int N, int M) {
        int[][] copied = new int[N][M];
        int N2 = N >> 1, M2 = M >> 1;
        for (int i = 0; i < N2; ++i) {
            for (int j = 0; j < M2; ++j) {
                copied[i][j] = A[i + N2][j];
                copied[i][j + M2] = A[i][j];
                copied[i + N2][j + M2] = A[i][j + M2];
                copied[i + N2][j] = A[i + N2][j + M2];
            }
        }
        return copied;
    }

    private static int[][] operation6(int[][] A, int N, int M) {
        int[][] copied = new int[N][M];
        int N2 = N >> 1, M2 = M >> 1;
        for (int i = 0; i < N2; ++i) {
            for (int j = 0; j < M2; ++j) {
                copied[i][j] = A[i][j + M2];
                copied[i][j + M2] = A[i + N2][j + M2];
                copied[i + N2][j + M2] = A[i + N2][j];
                copied[i + N2][j] = A[i][j];
            }
        }
        return copied;
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
}
