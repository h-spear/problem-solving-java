package baekjoon.prefixsum;

import java.io.*;
import java.util.*;

public class PrefixSum4 {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }

        for (int k = 0; k < m; ++k) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            bw.write("" + (prefixSum[j] - prefixSum[i - 1]));
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new PrefixSum4().solution();
    }
}
