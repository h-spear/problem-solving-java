// https://www.acmicpc.net/problem/1920
// Merge Sort, Binary Search 구현 연습 

package baekjoon.binarysearch;

import java.io.*;
import java.util.*;

public class FindNumber {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(A, 0, N - 1);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            if (binarySearch(A, N, Integer.parseInt(st.nextToken())))
                bw.write("1\n");
            else
                bw.write("0\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean binarySearch(int[] A, int N, int num) {
        int left = 0;
        int right = N - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (A[mid] == num) {
                return true;
            } else if (A[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    private static void mergeSort(int[] A, int from, int to) {
        int[] sorted = new int[to - from + 1];
        int size, low, mid, high;
        for (size = 1; size <= to; size <<= 1) {
            for (low = from; low <= to - size; low += (size << 1)) {
                mid = low + size - 1;
                high = Math.min(low + (size << 1) - 1, to);
                merge(A, sorted, low, mid, high);
            }
        }
    }

    private static void merge(int[] A, int[] sorted, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int idx = low;

        while (i <= mid && j <= high) {
            if (A[i] < A[j])
                sorted[idx++] = A[i++];
            else
                sorted[idx++] = A[j++];
        }

        while (i <= mid)
            sorted[idx++] = A[i++];

        while (j <= high)
            sorted[idx++] = A[j++];

        for (i = low; i <= high; ++i)
            A[i] = sorted[i];
    }
}
