package baekjoon.ternarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinaryAndTernarySearchPlay1 {

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; ++i) {
            A[i] = i;
        }

        int[] answer = new int[3];

        for (int i = 0, B, T; i < N; ++i) {
            B = binarySearch(A, i, 0, N - 1);
            T = ternarySearch(A, i, 0, N - 1);
            ++answer[(B < T ? 0 : (B > T ? 2 : 1))];
        }
        System.out.println(answer[0] + "\n" + answer[1] + "\n" + answer[2]);
        br.close();
    }
}
