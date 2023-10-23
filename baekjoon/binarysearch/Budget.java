// https://www.acmicpc.net/problem/2512

package baekjoon.binarysearch;

import java.io.*;
import java.util.*;

public class Budget {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] req = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			req[i] = Integer.parseInt(st.nextToken());
		}
		int budget = Integer.parseInt(br.readLine());

		int left = 0;
		int right = budget;
		int mid, res, answer = 0;
		while (left <= right) {
			mid = (left + right) >> 1;
			if ((res = simul(req, mid, budget)) != -1) {
				answer = res;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
		br.close();
	}

	private static int simul(int[] arr, int k, int t) {
		int sum = 0;
		int max = 0;
		int b;
		for (int num: arr) {
			b = Math.min(num, k);
			sum += b;
			max = Math.max(max, b);
		}
		if (sum > t)
			return -1;
		return max;
	}
}
