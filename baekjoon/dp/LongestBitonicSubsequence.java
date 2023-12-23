// https://www.acmicpc.net/problem/11054

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class LongestBitonicSubsequence {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] lisForward = lis(A, N);
        int[] lisBackward = reversed(lis(reversed(A), N));

        int answer = 0;
        for (int i = 0; i < N; ++i) {
            answer = Math.max(answer, lisForward[i] + lisBackward[i] - 1);
        }
        System.out.println(answer);
        br.close();
    }

    private static int[] lis(int[] A, int N) {
        int[] dp = new int[N];
        int[] queue = new int[N];
        int qIdx = 0;
        for (int i = 0; i < N; ++i) {
            if (qIdx == 0 || queue[qIdx - 1] < A[i]) {
                queue[qIdx++] = A[i];
                dp[i] = qIdx;
            } else {
                int idx = lowerBound(queue, 0, qIdx - 1, A[i]);
                queue[idx] = A[i];
                dp[i] = idx + 1;
            }
        }
        return dp;
    }

    private static int[] reversed(int[] A) {
        int N = A.length;
        int[] reversed = new int[N];
        for (int i = 0; i < N; ++i) {
            reversed[i] = A[N - i - 1];
        }
        return reversed;
    }

    private static int lowerBound(int[] arr, int left, int right, int k) {
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }
}
