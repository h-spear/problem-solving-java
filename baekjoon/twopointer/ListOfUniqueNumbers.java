// https://www.acmicpc.net/problem/13144

package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class ListOfUniqueNumbers {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] counter = new int[N + 1];
        int left = 0;
        long answer = 0;
        for (int right = 0; right < N; ++right) {
            ++counter[arr[right]];
            while (counter[arr[right]] > 1) {
                --counter[arr[left++]];
            }
            answer += (right - left + 1);
        }
        System.out.println(answer);
        br.close();
    }
}
