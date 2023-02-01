// https://www.acmicpc.net/problem/2003

package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class SumOfNumbers2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 1;
        int summation = arr[0];
        int answer = 0;
        while (r < n) {
            if (summation < m) {
                summation += arr[r];
                ++r;
            } else if (summation > m){
                summation -= arr[l];
                ++l;
            } else {
                ++answer;
                summation -= arr[l];
                ++l;
            }
        }

        while (l < r) {
            if (summation == m) {
                ++answer;
            }
            summation -= arr[l];
            ++l;
        }
        System.out.println(answer);
        br.close();
    }
}
