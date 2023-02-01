// https://www.acmicpc.net/problem/2805

package baekjoon.parametricsearch;

import java.io.*;
import java.util.*;

public class TreeCutting {

    private static int N, M;
    private static int[] heights;

    private static long simul(int h) {
        long res = 0;
        for (int height: heights) {
            res += Math.max(height - h, 0);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        heights = new int[N];

        int largest = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            heights[i] = Integer.parseInt(st.nextToken());
            largest = Math.max(largest, heights[i]);
        }

        int left = 0;
        int right = largest;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long res = simul(mid);
            if (res < M) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = Math.max(answer, mid);
            }
        }
        System.out.println(answer);
        br.close();
    }

}
