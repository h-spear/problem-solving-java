// https://www.acmicpc.net/problem/1806

package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class PartialSum {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int j = 1;
        int curr = arr[i];
        int answer = Integer.MAX_VALUE;
        while (j < n) {
            if (curr >= s) {
                answer = Math.min(answer, j - i);
                curr -= arr[i];
                ++i;
            } else {
                curr += arr[j];
                ++j;
            }
        }

        while (i < j) {
            if (curr >= s) {
                answer = Math.min(answer, j - i);
                curr -= arr[i];
                ++i;
            } else {
                break;
            }
        }

        if (answer == Integer.MAX_VALUE)
            answer = 0;
        System.out.println(answer);
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new PartialSum().solution();
    }
}
