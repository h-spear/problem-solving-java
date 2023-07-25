// https://www.acmicpc.net/problem/2750
// https://www.acmicpc.net/problem/2751
// https://www.acmicpc.net/problem/10989

package baekjoon.sorting;

import java.io.*;

public class NumberSorting {

    private static final int[] sorted = new int[10000001];

    private static void merge(int[] A, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int idx = left;

        while (i <= mid && j <= right) {
            if (A[i] < A[j]) {
                sorted[idx++] = A[i++];
            } else {
                sorted[idx++] = A[j++];
            }
        }

        while (i <= mid)
            sorted[idx++] = A[i++];
        while (j <= right)
            sorted[idx++] = A[j++];

        for (i = left; i <= right; ++i)
            A[i] = sorted[i];
    }

    private static void mergeSort(int[] A, int left, int right) {
        int low, mid, high;

        for (int size = 1; size <= right; size <<= 1) {
            for (int l = left; l <= right - size; l += (size << 1)) {
                low = l;
                mid = l + size - 1;
                high = Math.min(mid + size, right);
                merge(A, low, mid, high);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(arr, 0, arr.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
