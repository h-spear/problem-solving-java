package baekjoon.ternarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinaryAndTernarySearchPlay2 {

    private static int binarySearch(int[] A, int value, int left, int right) {
        int mid = (left + right) / 2;
        if (A[mid] == value) {
            return 0;
        }
        if (value < A[mid]) {
            return 1 + binarySearch(A, value, left, mid - 1);
        } else {
            return 1 + binarySearch(A, value, mid + 1, right);
        }
    }

    private static int ternarySearch(int[] A, int value, int left, int right) {
        int leftThird = left + (right - left) / 3;
        int rightThird = right - (right - left) / 3;
        if (A[leftThird] == value) {
            return 0;
        }
        if (A[rightThird] == value) {
            return 1;
        }
        if (value < A[leftThird]) {
            return 2 + ternarySearch(A, value, left, leftThird - 1);
        } else if (value < A[rightThird]) {
            return 2 + ternarySearch(A, value, leftThird + 1, rightThird - 1);
        } else {
            return 2 + ternarySearch(A, value, rightThird + 1, right);
        }
    }

    private static void cumulate(int[][] array) {
        for (int[] row: array) {
            for (int i = 0, end = row.length - 1; i < end; ++i) {
                row[i + 1] += row[i];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int MAX_NUM = 5050;

        int[] A = new int[MAX_NUM];
        for (int i = 0; i < MAX_NUM; ++i) {
            A[i] = i;
        }

        int[][] B = new int[MAX_NUM][];
        int[][] T = new int[MAX_NUM][];

        for (int n = 0; n < MAX_NUM; ++n) {
            B[n] = new int[n + 1];
            T[n] = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                B[n][i + 1] = binarySearch(A, i, 0, n - 1);
                T[n][i + 1] = ternarySearch(A, i, 0, n - 1);
            }
        }

        cumulate(B);
        cumulate(T);

        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append((T[N][E + 1] - T[N][S]) - (B[N][E + 1] - B[N][S]))
                    .append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}