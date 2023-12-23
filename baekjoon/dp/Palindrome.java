// https://www.acmicpc.net/problem/10942

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Palindrome {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        for (int i = 0; i < N; ++i) {
            int j = 0;
            int end = Math.min(i + 1, N - i);
            while (j < end && arr[i - j] == arr[i + j])
                ++j;
            dp1[i] = j;

            j = 0;
            end = Math.min(i + 1, N - i - 1);
            while (j < end && arr[i - j] == arr[i + j + 1])
                ++j;
            dp2[i] = j;
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            int[] dp = ((E - S) & 1) == 0 ? dp1 : dp2;
            sb.append(query(dp, S, E) ? "1" : "0").append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static boolean query(int[] dp, int s, int e) {
        int mid = (s + e) >> 1;
        return dp[mid] > (mid - s);
    }
}
