// https://www.acmicpc.net/problem/2143

package baekjoon.prefixsum;

import java.io.*;
import java.util.*;

public class SumOfTwoArray {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; ++i) {
			A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; ++i) {
			B[i] = B[i - 1] + Integer.parseInt(st.nextToken());
		}

		Map<Integer, Integer> counter = new HashMap<>();
		for (int i = 0, t; i <= n; ++i) {
			for (int j = i + 1; j <= n; ++j) {
				t = A[j] - A[i];
				counter.put(t, counter.getOrDefault(t, 0) + 1);
			}
		}

		long answer = 0;
		for (int i = 0; i < m; ++i) {
			for (int j = i + 1; j <= m; ++j) {
				answer += counter.getOrDefault(T - (B[j] - B[i]), 0);
			}
		}
		System.out.println(answer);
	    br.close();
	}
}
