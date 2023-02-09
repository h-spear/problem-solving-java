// https://www.acmicpc.net/problem/14002

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class LongestIncreasingSubsequence4 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxLength = 0;
        for (int i = 0; i < N; ++i) {
            int max = 0;
            for (int j = i - 1; j >= 0; --j) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            maxLength = Math.max(maxLength, dp[i]);
        }

        int[] lis = new int[maxLength];
        int k = maxLength;
        for (int i = N - 1; i >= 0; --i) {
            if (dp[i] == k) {
                lis[--k] = arr[i];
            }
        }

        bw.write(maxLength + "\n");
        for (int i = 0; i < maxLength; ++i) {
            bw.write(lis[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
