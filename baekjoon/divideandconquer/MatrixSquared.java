// https://www.acmicpc.net/problem/10830

package baekjoon.divideandconquer;

import java.io.*;
import java.util.*;

public class MatrixSquared {

    private static final int P = 1000;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) % P;
            }
        }
        printMatrix(pow(matrix, B));
        br.close();
    }

    private static void printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int[][] multiple(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int temp = 0;
                for (int k = 0; k < N; ++k) {
                    temp += matrix1[i][k] * matrix2[k][j];
                    temp %= P;
                }
                result[i][j] = temp;
            }
        }
        return result;
    }

    private static int[][] pow(int[][] matrix, long b) {
        if (b == 1)
            return matrix;

        int[][] temp = pow(matrix, b >> 1);
        if ((b & 1) == 1) {
            return multiple(multiple(temp, temp), matrix);
        } else {
            return multiple(temp, temp);
        }
    }
}